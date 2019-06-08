import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';
import {JwtHelperService} from '@auth0/angular-jwt';
import {Cookie} from 'ng2-cookies';

@Injectable()
export class RoleGuardService implements CanActivate {
  role: string;

  constructor(public router: Router, public jwtHelper: JwtHelperService) {
    this.jwtHelper = new JwtHelperService();
  }


  public isAuthenticated(): boolean {
    const token = Cookie.get('access_token');
    console.log(token);

    if (token == null) {
      return false;
    }
    // Check whether the token is expired and return
    // true or false
    return !this.jwtHelper.isTokenExpired(token);
  }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    // this will be passed from the route config
    // on the data property
    this.getRole();
    const expectedRole = route.data.expectedRole;
    if (
      !this.isAuthenticated() ||
      this.role !== expectedRole
    ) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }

  public getRole() {
    const token = Cookie.get('access_token');
    // decode the token to get its payload
    const tokenPayload = this.jwtHelper.decodeToken(token);
    console.log(JSON.stringify(tokenPayload));
    console.log(tokenPayload.authorities);
    const role: string[] = tokenPayload.authorities;
    this.role = role[0];
    return role[0];
  }
}
