import { Component, OnInit } from '@angular/core';
import { UserInfoService } from '../../services/user-info.service';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Dashboard', icon: 'dashboard', class: '' },
    { path: '/establishments', title: 'Estabelecimento', icon: 'local_bar', class: '' },
    { path: '/users', title: 'Usuário', icon: 'person', class: '' },
    { path: '/scheudules', title: 'Agenda', icon: 'date_range', class: '' },
    { path: '/tasks', title: 'Tarefas', icon: 'work', class: '' },
    { path: '/financials', title: 'Financeiro', icon: 'money', class: '' },
    { path: '/reports', title: 'Relatórios', icon: 'report', class: '' }
    // { path: '/user-profile', title: 'User Profile',  icon:'person', class: '' },
    // { path: '/table-list', title: 'Table List',  icon:'content_paste', class: '' },
    // { path: '/typography', title: 'Typography',  icon:'library_books', class: '' },
    // { path: '/icons', title: 'Icons',  icon:'bubble_chart', class: '' },
    // { path: '/maps', title: 'Maps',  icon:'location_on', class: '' },
    // { path: '/notifications', title: 'Notifications',  icon:'notifications', class: '' },
];

@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
    menuItems: any[];

    constructor(private userInfoService: UserInfoService) {}

    ngOnInit() {
        this.menuItems = ROUTES.filter(menuItem => menuItem);
    }
    isMobileMenu() {
        if ($(window).width() > 991) {
            return false;
        }
        return true;
    }

    logOut() {
        this.userInfoService.removeUserInfo();
    }
}
