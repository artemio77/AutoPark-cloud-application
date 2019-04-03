import {JwtModule, JwtModuleOptions} from '@auth0/angular-jwt';
import {NgModule} from '@angular/core';
import {Cookie} from 'ng2-cookies';


export function getToken(): string {
  if (Cookie.get('access_token').length > 0) {
    return localStorage.getItem('access_token');
  }
  if (localStorage.getItem('access_token').length > 0) {
    return localStorage.getItem('access_token');
  }
  return null;
}

const JWT_Module_Options: JwtModuleOptions = {
  config: {
    tokenGetter: getToken,
  }
};

@NgModule({
  imports: [
    JwtModule.forRoot(JWT_Module_Options)
  ]
})
export class AppGuardModule {
}
