import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpRequest,  HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable} from 'rxjs';
import 'rxjs/add/operator/catch';
import { UserInfoService, LoginInfoInStorage} from '../user-info.service';
import { AppConfig } from '../../app-config';
import { UploadQueue } from '../../components/entities/uploadqueue';
import { FileAttached } from '../../components/entities/FileAttached';


@Injectable()
export class ApiRequestService {

    constructor(
        private appConfig:AppConfig,
        private http: HttpClient,
        private router:Router,
        private userInfoService:UserInfoService
    ) {}

    /**
     * This is a Global place to add all the request headers for every REST calls
     */
    getHeaders(isFile):HttpHeaders {
        let headers = new HttpHeaders();
        let token = this.userInfoService.getStoredToken();
        
        if(isFile) {
            headers = headers.append('Content-Type', 'application/json');
        }
        
        if (token !== null) {
            headers = headers.append("Authorization", token);
        }
        return headers;
    }

    get(url:string, urlParams?:HttpParams):Observable<any>{
        let me = this;
        return this.http.get(this.appConfig.baseApiPath + url, {headers:this.getHeaders(true),  params:urlParams} )
            .catch(function(error:any){
                console.log("Some error in catch");
                if (error.status === 401 || error.status === 403){
                    me.router.navigate(['/login']);
                }
                return Observable.throw(error || 'Server error')
            });
    }

    post(url:string, body:Object):Observable<any>{
        let me = this;
        return this.http.post(this.appConfig.baseApiPath + url, JSON.stringify(body), { headers:this.getHeaders(true)})
            .catch(function(error:any){
                if (error.status === 401){
                    me.router.navigate(['/login']);
                }
                return Observable.throw(error || 'Server error')
            });
    }

    put(url:string, body:Object):Observable<any>{
        let me = this;
        return this.http.put(this.appConfig.baseApiPath + url, JSON.stringify(body), { headers:this.getHeaders(true)})
            .catch(function(error:any){
                if (error.status === 401){
                    me.router.navigate(['/login']);
                }
                return Observable.throw(error || 'Server error')
            });
    }

    delete(url:string):Observable<any>{
        let me = this;
        return this.http.delete(this.appConfig.baseApiPath + url, { headers:this.getHeaders(true)})
            .catch(function(error:any){
                if (error.status === 401){
                    me.router.navigate(['/login']);
                }
                return Observable.throw(error || 'Server error')
            });
    }

    postFiles(url:string, uploadQueue: UploadQueue):Observable<any>{
        if (uploadQueue) {
            const formData = new FormData();
            //formData.append(UploadQueue.name, uploadQueue.file);
            formData.append('file', uploadQueue.file);
            const uploadReq = new HttpRequest('POST',this.appConfig.baseApiPath + url, formData, {
                headers : this.getHeaders(false),
                reportProgress: true,
            },);
    
            return this.http.request<FileAttached>(uploadReq);
          }
    }

}
