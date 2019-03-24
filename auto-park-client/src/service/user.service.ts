import {Injectable} from '@angular/core';

import {User} from '../model/user';
import {Observable} from 'rxjs/index';

import {Http} from '@angular/http';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Cookie} from 'ng2-cookies';
import {environment} from '../environments/environment';
import {map} from 'rxjs/operators';


@Injectable()
export class UserService {


  private oauthApiUrl = environment.apiUrl;

  constructor(private http: Http,
              private httpClient: HttpClient) {
  }

  public getDrivers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.oauthApiUrl + '/user-management-service/get/drivers', {responseType: 'json'});
  }

  public createUser(user) {
    console.log(user);
    return this.httpClient.put(this.oauthApiUrl + '/user-management-service/create', user);
  }

  public getUserCode(code: number) {
    return this.http.get(this.oauthApiUrl + '/auth/' + code);
  }

  public getUserByEmail(email: string): Observable<User> {
    const params = new HttpParams().set('email', email);
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.httpClient.post<User>(this.oauthApiUrl + '/user-management-service/get/email', {headers: headers}, {params: params});
  }

  public isEmailExists(email: String): Observable<any> {
    return this.httpClient.get<boolean>(this.oauthApiUrl + '/auth/exist/' + email);
  }

}
