import { Injectable } from '@angular/core';

import { Http, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { Establishment } from '../../establishments/model/Establishment';
import { AppComponent } from '../../app.component';
import { PaginationPage } from '../model/PaginationPage';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS, HttpEventType, HttpRequest, HttpParams } from '@angular/common/http';
import { UploadQueue } from '../../components/entities/uploadqueue';
import { ApiRequestService } from '../../services/api/api-request.service';
@Injectable()
export class EstablishmentService {
    

    constructor(private _http: HttpClient,   private apiRequest: ApiRequestService) {}

    data: Observable<Establishment> = null;

    getAll() {
        let params: HttpParams = new HttpParams();
        return this.apiRequest.get("/rest/establishment",params).map(data => data);
    }

    getById(id): Observable<Establishment> {
        let params: HttpParams = new HttpParams();
        if (id){
            params = params.append('id', id.toString());
        }
        return this.apiRequest.get("/rest/establishment/"+ id,params).map(res =>  res)
    }

    save(establishment): Observable<Establishment> {
       return  this.apiRequest.post('/rest/establishment', establishment).map(res => res);
    }

    remove(id) {
        return this.apiRequest.delete('/rest/establishment/' + id).map(res => res);
    }

    update(establishment) {
        return  this.apiRequest.put('/rest/establishment/', establishment).map(res => res);
    }

    downloadFile(link) {
        return this._http
        .get(link)
        .map(res => {
            return {
            responseType: 'blob'
            };
        })
        .subscribe(res => {
            console.log('start download:',res);
            var url = window.URL.createObjectURL(res);
            var a = document.createElement('a');
            document.body.appendChild(a);
            a.setAttribute('style', 'display: none');
            a.href = url;
            a.click();
            window.URL.revokeObjectURL(url);
            a.remove(); // remove the element
            }, error => {
            console.log('download error:', JSON.stringify(error));
            }, () => {
            console.log('Completed file download.')
            });
    }

      
}
