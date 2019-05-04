import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {Cookie} from 'ng2-cookies';
import {Headers, Http, RequestOptions} from '@angular/http';
import {MatSnackBar} from '@angular/material';
import {HttpClient} from '@angular/common/http';
import {environment} from '../environments/environment';

@Injectable()
export class AppService {
  private login;

  private oauthUrl = environment.oauthServiceUrl;

  constructor(private _router: Router, private _http: Http, private httpClient: HttpClient, private snackBar: MatSnackBar) {

  }

  obtainAccessToken(loginData) {
    const params = new URLSearchParams();
    params.append('username', loginData.login);
    params.append('password', loginData.password);
    params.append('grant_type', 'password');
    params.append('client_id', environment.oauthClientId);

    const headers = new Headers({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Basic ' + btoa(environment.oauthClientId + ':' + environment.oauthClientSecret)
    });
    const options = new RequestOptions({headers: headers});
    console.log(params.toString());
    this._http.post(this.oauthUrl + '/oauth/token', params.toString(), options)
      .subscribe(
        data => {
          this.saveToken(data.json(), loginData.login);
          this.setAuthLogin(loginData.login);
        },
        err => this.snackBar.open('Invalid Cradentials, please try again', 'Log In', {
          duration: 2000
        }));
  }

  saveToken(token, login) {
    const expireDate = new Date().getTime() + (1000 * token.expires_in);
    Cookie.set('access_token', token.access_token, expireDate);
    localStorage.setItem('login', login);
    console.log('Obtained Access token');
    this.setAuthLogin(login);
    localStorage.setItem('token', token);
    this._router.navigate(['application']);
  }

  setAuthLogin(login) {
    this.login = login;
  }

  logout() {
    localStorage.removeItem('token');
    Cookie.delete('access_token');
    console.log(Cookie.get('access_token'));
    this._router.navigate(['login']);
  }
}
