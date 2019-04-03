import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {JwtHelperService} from '@auth0/angular-jwt';
import {Observable} from 'rxjs';
import {Cookie} from 'ng2-cookies';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {
  constructor(public router: Router, public jwtHelper: JwtHelperService) {
    this.jwtHelper = new JwtHelperService();
  }


  public isAuthenticated(): boolean {
    const token = Cookie.get('access_token');
    if (token == null) {
      return false;
    }
    // Check whether the token is expired and return
    // true or false
    return !this.jwtHelper.isTokenExpired(token);
  }

  canActivate(): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.isAuthenticated()) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
