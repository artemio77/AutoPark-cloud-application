import {JwtModule, JwtModuleOptions} from '@auth0/angular-jwt';
import {NgModule} from '@angular/core';


export function getToken(): string {
  return localStorage.getItem('access_token');
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
