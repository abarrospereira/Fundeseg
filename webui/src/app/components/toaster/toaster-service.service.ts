import { Component, OnInit, Injectable } from '@angular/core';
import { TypeNotification } from './type.notification';
declare var $: any;

@Injectable()
export class ToasterService {
    
    constructor() {}

    showNotification(typeError: TypeNotification, message: string) {
        const type = ['', 'info', 'success', 'warning', 'danger'];

        const color = Math.floor(Math.random() * 4 + 1);

        $.notify(
            {
                icon: 'doasdasdasdne',
                message: message,
                progressbar: true
            },
            {
                type: type[typeError + 1],
                timer: 4000,
                placement: {
                    from: 'top',
                    align: 'right'
                },
                template:
                    '<div data-notify="container" class="col-xl-4 col-lg-4 col-11 col-sm-4 col-md-4 alert alert-{0} alert-with-icon" role="alert">' +
                    '<button mat-button  type="button" aria-hidden="true" class="close mat-button" data-notify="dismiss">  <i class="material-icons">close</i></button>' +
                    '<i class="material-icons" data-notify="icon">done</i> ' +
                    '<span data-notify="title">{1}</span> ' +
                    '<span data-notify="message">{2}</span>' +
                    '<div class="progress" data-notify="progressbar">' +
                    '<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
                    '</div>' +
                    '<a href="{3}" target="{4}" data-notify="url"></a>' +
                    '</div>'
            }
        );
    }
    ngOnInit() {}
}
