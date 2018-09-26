import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort, MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Establishment } from '../establishments/model/Establishment';
import { EstablishmentService } from '../establishments/services/establishment.service';
import { PaginationPage } from './model/PaginationPage';
import { Router } from '@angular/router';
import { NgbModal, NgbActiveModal, NgbModalOptions, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { ModalComponent } from '../components/modal/modal.component';
import { ToasterService } from '../components/toaster/toaster-service.service';
import { TypeNotification } from '../components/toaster/type.notification';
import { ModalProtocolComponent } from '../modalProtocol/modal.protocol.component';

@Component({
    selector: 'app-establishments',
    templateUrl: './establishments.component.html',
    styleUrls: ['./establishments.component.css']
})
export class EstablishmentsComponent implements OnInit {
    displayedColumns = ['id', 'name', 'cnpj', 'responsible','city', 'edit'];
    dataSource: MatTableDataSource<Establishment>;

    @ViewChild(MatPaginator)
    paginator: MatPaginator;
    @ViewChild(MatSort)
    sort: MatSort;
    userlist: any;

    constructor(
        private establishmentService: EstablishmentService,
        private router: Router,
        public dialog: MatDialog,
        public toasterService: ToasterService
    ) {
        const users: Establishment[] = [];

        this.establishmentService.getAll().subscribe(establishments => {
            this.dataSource = new MatTableDataSource(establishments.content);
            this.userlist = establishments.content;
            this.dataSource.paginator = this.paginator;
        });
        //this.dataSource = new MatTableDataSource(users);
    }

    ngOnInit() {
        this.refreshGrid();
    }

    ngAfterViewInit() {}

    applyFilter(filterValue: string) {
        filterValue = filterValue.trim(); // Remove whitespace
        filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
        this.dataSource.filter = filterValue;
    }

    getTitle() {
        return 'Estabelecimentos';
    }

    newEstablishment() {
        this.router.navigate(['establishments/register']);
    }

    animal: string;
    name: string;

    private refreshGrid(): void {
        this.establishmentService.getAll().subscribe(establishments => {
            this.dataSource = new MatTableDataSource(establishments.content);
            this.dataSource.paginator = this.paginator;
        });
    }

    private delete(id): void {
        const dialogRef = this.dialog.open(ModalComponent, {
            width: '300px',
            data: { name: this.name, animal: this.animal }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.establishmentService.remove(id).subscribe(data => {
                    this.toasterService.showNotification(
                        TypeNotification.success,
                        'Estabelecimento Excluído com sucesso!'
                    );
                    this.refreshGrid();
                });
            }
        });
    }


    private edit(id) {
        this.router.navigate(['/establishments/edit', id, 'edit']);
    }

    private view(id) {
        this.router.navigate(['/establishments/edit', id, 'view']);
    }

}
