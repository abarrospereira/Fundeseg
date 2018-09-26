import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { Establishment } from '../model/Establishment';
import { Router, ActivatedRoute } from '@angular/router';
import { EstablishmentService } from '../services/establishment.service';
import { ToasterService } from '../../components/toaster/toaster-service.service';
import { TypeNotification } from '../../components/toaster/type.notification';
import { Uploader } from '../../components/entities/uploader';
import { UploadQueue } from '../../components/entities/uploadqueue';
import { VALID } from '@angular/forms/src/model';
import { HttpEventType } from '@angular/common/http';
import { FileAttached } from '../../components/entities/FileAttached';
import { EstablishmentAttachments } from '../model/EstablishmentAttachments';
import { BasicValidators } from './validator/basic-validators';
import { UploaderService } from '../../services/uploader.service';
import { MatDialog } from '@angular/material';
import { ModalPdfComponent } from '../../components/modalPdf/modal.pdf.component';
import { ModalProtocolComponent } from '../../modalProtocol/modal.protocol.component';
import { UserInfoService, UserInStorage } from '../../services/user-info.service';


// const URL = '/api/';
const URL = 'https://evening-anchorage-3159.herokuapp.com/api/';

@Component({
    selector: 'app-establishment-register',
    templateUrl: './establishment.register.component.html',
    styleUrls: ['./establishment.register.component.css']
})
export class EstablishmentRegisterComponent implements OnInit {
    formGroup: FormGroup;
    formGroupAdd: FormGroup;
    title: string;
    establishment: Establishment = new Establishment();
    persistedId: number;
    disabled: boolean;
    uploaderQueue: UploadQueue ;
    uploader: Uploader = new Uploader();
    establishmentAttachments: EstablishmentAttachments[] = [];
    fileAttached: FileAttached;



    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private route: ActivatedRoute,
        private establishmentService: EstablishmentService,
        private uploaderService: UploaderService,
        private toasterService: ToasterService,
        public dialog: MatDialog,
        private userInfoService: UserInfoService

    ) {}

    ngOnInit() {
        var id = this.route.params.subscribe(params => {
            this.persistedId = params['id'];
            this.disabled = params['action'] == "view" ? true : false;

            if(this.persistedId){
                this.title
            }

            this.title = this.persistedId ? 'Edição' : 'Novo';

            if (!this.persistedId) return;

            this.establishmentService.getById(this.persistedId).subscribe(
                establishment =>  { 
                    (this.establishment = establishment);
                    this.refreshGrisAttachemnts();
                },
                response => {
                    if (response.status == 404) {
                        this.router.navigate(['NotFound']);
                    }
                }
            );

            

        });

        this.formGroup = this.formBuilder.group({
            name: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required, Validators.minLength(5)]),
            address: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required, Validators.minLength(5)]),
            cnpj: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required,BasicValidators.cnpj]),
            cep: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required, Validators.minLength(5)]),
            number: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required, Validators.minLength(5)]),
            city: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required, Validators.minLength(5)]),
            telephone: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required, Validators.minLength(5)]),
            responsible: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required, Validators.minLength(5)]),
            cpf: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required, BasicValidators.cpf]),
            category: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required]),
            activity: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required]),
            observation: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required]),
            id: this.formBuilder.control('')
        });

        this.formGroupAdd = this.formBuilder.group({
            title: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required, Validators.minLength(5)]),
            description: this.formBuilder.control({value: '', disabled: this.disabled}, [Validators.required, Validators.minLength(5)]),
        });
    }

     refreshGrisAttachemnts() {
        this.establishmentAttachments =   this.establishment.establishmentAttachments.map(res => {
            return new EstablishmentAttachments('',res.attachment_id,res.fileName,res.title,res.description,res.size,100,res.fileAttached, res.link);
        });

    }

    download(link) {

            const dialogRef = this.dialog.open(ModalPdfComponent, {
                width: '1000px',
                height: '800px',
                data: { url: link },
                hasBackdrop: false
            });
    
            dialogRef.afterClosed().subscribe(result => {
                if (result) {
                  
                }
            });
        //this.uploaderService.download(attachment_id);
        
    }

     add () {
        if(this.isFormValidUpload()) {
            var result,
            uploadQueuedd = this.formGroupAdd.value;

            let establishmentAttachments = new EstablishmentAttachments(this.uploader.queue.file,'',this.uploader.queue.file.name,uploadQueuedd.title,uploadQueuedd.description,this.uploader.queue.file.size);
            this.establishmentAttachments.push(establishmentAttachments);

            this.uploaderService.upload(this.uploader.queue).subscribe(event => {
                console.log(event)
                if (event.type === HttpEventType.UploadProgress) {           
                    establishmentAttachments.progress = Math.round(100 * event.loaded / event.total);
                }
                else if (event.type === HttpEventType.Response){
                    establishmentAttachments.fileAttached = event.body;
                    establishmentAttachments.attachment_id = establishmentAttachments.fileAttached.fileId;
                    establishmentAttachments.link = establishmentAttachments.fileAttached.link;
                }
              } );

           
              this.uploader.title = '';
              this.uploader.description = '';
        }
    }
    save() {
        var result,
            establishmentValue = this.formGroup.value;

        this.establishment = establishmentValue;

       this.establishment.establishmentAttachments =  this.establishmentAttachments;

        if (this.isFormValidFields()) {
            if (this.persistedId) {
                establishmentValue.id = this.persistedId;
                result = this.establishmentService.update(this.establishment);
            } else {
                result = this.establishmentService.save(this.establishment);
            }
            result.subscribe(data =>  { 
                this.toasterService.showNotification(TypeNotification.success, 'Estabelecimento salvo com sucesso!');
                this.viewProtocol(this.establishment);
            });
        }
    }


  
   
    onFileSelected(event: EventTarget) {
        let eventObj: MSInputMethodContext = <MSInputMethodContext>event;
        let target: HTMLInputElement = <HTMLInputElement>eventObj.target;
        let files: FileList = target.files;
        let file = files[0];
        if (file) {
            
          this.uploader.queue = new UploadQueue(file);;
          console.log( this.uploader);
        }
        
    }

    downloadItem(link) {
        this.establishmentService.downloadFile(link);
    }
  
    removeAttachments(item) {
        this.establishmentAttachments.forEach( (item, index) => {
            if(item === item) this.establishmentAttachments.splice(index,1);
          });

    }

    private viewProtocol(establishment: Establishment) {
        let userObj:UserInStorage = this.userInfoService.getUserInfo();
        
        const dialogRef = this.dialog.open(ModalProtocolComponent, {
            width: '550px',
            height: '460px',
            data: { url: 'teste', category: establishment.category, user: userObj.displayName}
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
              this.router.navigate(['/establishments']);
            }
        });
    }

    private isFormValidFields() {
        return this.formGroup.status === 'VALID';
    }

    private isFormValidUpload() {
        return this.formGroupAdd.status === 'VALID';
    }

  
    cancel() {
        this.router.navigate(['/establishments']);
    }
   
}
