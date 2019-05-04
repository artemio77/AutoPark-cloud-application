import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserComponent} from '../app/application/user.component';
import {AppComponent} from '../app/app.component';
import {LoginRegistrationComponent} from '../app/loginRegistration/login.registration.component';
import {UserContentComponent} from '../app/application/user-application/user-content.component';
import {UserManagerTransportComponent} from '../app/application/user-manager-transport/user-manager-transport.component';
import {UserManagerRouteComponent} from '../app/application/user-manager-route/user-manager-route.component';
import {UserManagerDriverComponent} from '../app/application/user-manager-driver/user-manager-driver.component';
import {RoleGuardService} from '../service/role-guard.service';

const itemRoutes: Routes = [
  {
    path: '', component: UserComponent,
  },
  {
    path: 'view/:id', component: UserContentComponent, canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_DRIVER'
    }
  },
  {
    path: 'transport/:id', component: UserManagerTransportComponent, canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_MANAGER'
    }
  },
  {
    path: 'route/:id', component: UserManagerRouteComponent, canActivate: [RoleGuardService], data: {
      expectedRole: 'ROLE_MANAGER'
    }
  },
  {
    path: 'drivers/:id', component: UserManagerDriverComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_MANAGER'
    }
  },
];

const routes: Routes = [
  {
    path: 'application',
    component: UserComponent,
    children: itemRoutes
  },
  {
    path: 'login',
    component: LoginRegistrationComponent,
  },
  {
    path: '',
    component: AppComponent
  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
