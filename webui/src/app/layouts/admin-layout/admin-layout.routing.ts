import { Routes } from '@angular/router';

import { DashboardComponent } from '../../dashboard/dashboard.component';
import { AuthGuard } from '../../services/auth_guard.service';
import { EstablishmentsComponent } from '../../establishments/establishments.component';
import { EstablishmentRegisterComponent } from '../../establishments/register/establishment.register.component';
// import { UserProfileComponent } from '../../user-profile/user-profile.component';
// import { TableListComponent } from '../../table-list/table-list.component';
// import { MapsComponent } from '../../maps/maps.component';
// import { NotificationsComponent } from '../../notifications/notifications.component';
// import { LoginComponent } from '../../security/login/login.component';
// import { UrlPermission } from '../../urlPermission/url.permission';

export const AdminLayoutRoutes: Routes = [
 
    { path: 'dashboard',      component: DashboardComponent  ,  canActivate:[AuthGuard]},
    // { path: 'user-profile',   component: UserProfileComponent },
    // { path: 'table-list',     component: TableListComponent },
    // { path: 'maps',           component: MapsComponent },
    // { path: 'notifications',  component: NotificationsComponent },
    { path: 'establishments',  component: EstablishmentsComponent },
    {
        path: 'establishments',
        children: [
          {path: 'register', component: EstablishmentRegisterComponent}, 
          {path: 'edit/:id/:action', component: EstablishmentRegisterComponent}, 
        ]
      },
];
