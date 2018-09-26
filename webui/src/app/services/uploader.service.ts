import { Injectable } from '@angular/core';

import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS, HttpEventType, HttpRequest, HttpEvent } from '@angular/common/http';
import { AppComponent } from '../app.component';
import { UploadQueue } from '../components/entities/uploadqueue';
import { FileAttached } from '../components/entities/FileAttached';
import { ApiRequestService } from './api/api-request.service';

@Injectable()
export class UploaderService {

  constructor(private _http: HttpClient,  private apiRequest: ApiRequestService) {}

	upload(uploadQueue: UploadQueue) {
            return this.apiRequest.postFiles("rest/uploader/upload", uploadQueue);
  }


  download(attachment_id) {
  }

    // pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    //   const formdata: FormData = new FormData();
    //   formdata.append('file', file);
    //   const req = new HttpRequest('POST', this.url, formdata, {
    //     reportProgress: true,
    //     responseType: 'text'
    //   }
    //   );
    //   return this._http.request(req);
    // }
  
      
}
