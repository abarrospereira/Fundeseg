import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule, ErrorHandler } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';


import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';

import { AppComponent } from './app.component';



import {
  AgmCoreModule
} from '@agm/core';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NgbModule, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { MatDialogContent } from '@angular/material';
import {ToasterService} from './components/toaster/toaster-service.service'
import { TextMaskModule } from 'angular2-text-mask';
import { AppConfig } from './app-config';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './services/auth_guard.service';
import { UserInfoService } from './services/user-info.service';
import { TranslateService } from './services/api/translate.service';
import { ApiRequestService } from './services/api/api-request.service';
import { LoginService } from './services/api/login.service';
import { EstablishmentService } from './establishments/services/establishment.service';
import { UploaderService } from './services/uploader.service';

1
@NgModule({
  imports: [
    
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    ComponentsModule,
    RouterModule,
    AppRoutingModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'YOUR_GOOGLE_MAPS_API_KEY'
    }),
    NgbModule.forRoot()  ,
    TextMaskModule
  ],
  
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    LoginComponent
  ],
  providers:[
    AuthGuard,
    UserInfoService,
    TranslateService,
    ApiRequestService,
    EstablishmentService,
    ToasterService,
    LoginService,
    UploaderService,
    AppConfig,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
