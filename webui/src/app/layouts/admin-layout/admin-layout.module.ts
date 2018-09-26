import { NgModule, ErrorHandler } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../dashboard/dashboard.component';
// import { UserProfileComponent } from '../../user-profile/user-profile.component';
// import { TableListComponent } from '../../table-list/table-list.component';
// import { MapsComponent } from '../../maps/maps.component';
// import { NotificationsComponent } from '../../notifications/notifications.component';
import { EstablishmentsComponent } from '../../establishments/establishments.component';

// import { LoginComponent } from '../../security/login/login.component';

import {
  MatButtonModule,
  MatInputModule,
  MatRippleModule,
  MatTooltipModule,
  MatAutocompleteModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatStepperModule} from '@angular/material';
//import { EstablishmentRegisterComponent } from '../../establishments/register/establishment.register.component';
import { ModalComponent } from '../../components/modal/modal.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { DataTableModule } from "angular2-datatable";
import { TextMaskModule } from 'angular2-text-mask';
import { EstablishmentRegisterComponent } from '../../establishments/register/establishment.register.component';
import { ModalPdfComponent } from '../../components/modalPdf/modal.pdf.component';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { ModalProtocolComponent } from '../../modalProtocol/modal.protocol.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    MatButtonModule,
    MatRippleModule,
    MatInputModule,
    MatTooltipModule,
    MatAutocompleteModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatDatepickerModule,
    MatDialogModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule ,
    DataTableModule,
    PdfViewerModule
    
  ],
  declarations: [
     DashboardComponent,
    // UserProfileComponent,
    // TableListComponent,
    // MapsComponent,
    // NotificationsComponent,
    EstablishmentsComponent,
   EstablishmentRegisterComponent,
    ModalComponent,ModalPdfComponent, ModalProtocolComponent
    ],
    entryComponents: [EstablishmentsComponent ,ModalPdfComponent,ModalComponent, ModalProtocolComponent],
    // bootstrap: [EstablishmentsComponent],

})

export class AdminLayoutModule {}
