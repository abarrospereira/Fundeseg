import { Component, Inject } from "@angular/core";
import { Router } from "@angular/router";
import { MatDialog, MAT_DIALOG_DATA } from "@angular/material";
import { ModalModel } from "./modal.model";

@Component({
    selector: 'modal.component',
    templateUrl: 'modal.component.html',
  })
  export class ModalComponent {


constructor(private router: Router,public dialog: MatDialog,@Inject(MAT_DIALOG_DATA) public data: ModalModel) { 
}

description: string;

openDialog(): void {
    const dialogRef = this.dialog.open(ModalComponent, {
        panelClass: 'my-centered-dialog',
        width: '512px',
        data: {description: this.description}
    });

    dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
    });
  }
}
  