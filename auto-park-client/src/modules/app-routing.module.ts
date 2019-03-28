import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserComponent} from '../app/application/user.component';
import {AppComponent} from '../app/app.component';
import {LoginRegistrationComponent} from '../app/loginRegistration/login.registration.component';
import {UserContentComponent} from '../app/application/user-application/user-content.component';
import {AuthGuardService} from '../service/auth-guard.service';
import {UserManagerTransportComponent} from '../app/application/user-manager-transport/user-manager-transport.component';
import {UserManagerRouteComponent} from '../app/application/user-manager-route/user-manager-route.component';
import {UserManagerDriverComponent} from '../app/application/user-manager-driver/user-manager-driver.component';

const itemRoutes: Routes = [
  {path: 'context', component: UserContentComponent},
  {path: 'transport', component: UserManagerTransportComponent},
  {path: 'route', component: UserManagerRouteComponent},
  {path: 'drivers', component: UserManagerDriverComponent},
];

const routes: Routes = [
  {
    path: 'application/:id',
    component: UserComponent,
    children: itemRoutes,
    canActivate: [AuthGuardService]
  },
  {
    path: 'login',
    component: LoginRegistrationComponent
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
