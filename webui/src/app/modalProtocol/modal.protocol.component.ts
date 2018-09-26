import { Component, Inject, ViewChild, ElementRef } from "@angular/core";
import { Router } from "@angular/router";
import { MatDialog, MAT_DIALOG_DATA } from "@angular/material";
import { ModalProtocolModel } from "./modal.protocol.model";
import { SafeHtml } from "@angular/platform-browser";
import * as jspdf from 'jspdf';  
import html2canvas from 'html2canvas'; 


@Component({
    selector: 'modal.protocol.component',
    templateUrl: 'modal.protocol.component.html',
  })
  export class ModalProtocolComponent {

    public innerHtml: SafeHtml;
    public url: string;
    _sanitizer: any;
    @ViewChild('content') content: ElementRef;
    
constructor(private router: Router,public dialog: MatDialog,@Inject(MAT_DIALOG_DATA) public data: ModalProtocolModel) { 
}

description: string;

cancel() {
  
}

captureScreen()  
{  
    window.print();
} 

openDialog(): void {
 
    const dialogRef = this.dialog.open(ModalProtocolComponent, {
        panelClass: 'my-centered-dialog',
        width: '512px',
        data: {url: "https://00e9e64bacb788650ac4e9470710bb7a202846890e5aaeb672-apidata.googleusercontent.com/download/storage/v1/b/fundeseg-attachments/o/sample.pdf?qk=AD5uMEu4ab0YmROnrUpKtd3xLyFEzQDIGrPumoRQbeAxtfXcKEnTwQukCYW54mIIRD1nVc-YNsx7hPeGz_wPiSWcNg9DYiYXRCyn4FwOTbMg55tejzwPSbFrGSpicrMCmBXik8goM4QzMHmOTWIGwbEeMUXC3OrB4_TSXN0UtcfILGjnsatCRms1meH64i4H4gYrRUi3mcZsv4LlxZNjQWNINZssn7qHWKljLpIkqEgFgVCRW2dXBngMfm2UGJdyj8BIs1RNO1hUzY9Eh9SaGAeUZimuKpVl7gaFOQzCq9xfQHJ0nKzpRjvusoARS_VMGy3xB2D7S2BKWcoT91QjG8rTlUM8QJp9OcRHTui28xwnaHZSkeNlD9bRXHEzUWnsOzGpGIJJjamHmHHTpOHI07-Gr2BMIvjJJsSD3N7jto6Rg53K8raEtXMRDwI33vpdkwy3bw0ZdxNvPw07MT50QphKzsnDYjHVN9jOqEVFOHWQ0fXw_1nZBL4NZN1JrZmpBY6aDiYfzk4fAuygqUc5VS-SVznoGzUOVIUXeyJ7CGC-wSJrVZc5wuJKTa0zjA0LyyQY37EXYM-U2AWFpOgAEAeY1wsueA-vOLyozGxv1x_sQ7ZNw9xcObv2HddmRvRtKleFg59ZtNCDWnUSb5J7BWqIh06OvBGfchxAy5hvVf2u6-DiLM9uN38qjexUGw2DfK6xbuLwTp93m-cvEXXo_FMGqU19db3Hv2f42fc4GmxV6egX951RPk3bBbrSpcjyB4da-v2w9iq3"}
    });


    dialogRef.afterClosed().subscribe(result => {
        var data = document.getElementById('contentToConvert');  
      html2canvas(data).then(canvas => {  
        // Few necessary setting options  
        var imgWidth = 208;   
        var pageHeight = 295;    
        var imgHeight = canvas.height * imgWidth / canvas.width;  
        var heightLeft = imgHeight;  
    
        const contentDataURL = canvas.toDataURL('image/png')  
        let pdf = new jspdf('p', 'mm', 'a4'); // A4 size page of PDF  
        var position = 0;  
        pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight)  
        pdf.save('MYPdf.pdf'); // Generated PDF   
      });  
    });
  }
}
  