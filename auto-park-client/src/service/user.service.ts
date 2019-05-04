import {Injectable} from '@angular/core';

import {User} from '../model/user';
import {Observable} from 'rxjs/index';

import {Http} from '@angular/http';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Cookie} from 'ng2-cookies';
import {environment} from '../environments/environment';
import {Role} from '../model/create/role.type';
import {TransportEntity} from '../model/transport';
import {RouteEntity} from '../model/route';


@Injectable()
export class UserService {


  private oauthApiUrl = environment.apiUrl;

  constructor(private http: Http,
              private httpClient: HttpClient) {
  }

  public getAllUserList(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.oauthApiUrl + '/user-management-service/get/all', {responseType: 'json'});
  }

  public getDrivers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.oauthApiUrl + '/user-management-service/get/drivers', {responseType: 'json'});
  }

  public createUser(user) {
    console.log(user);
    return this.httpClient.put(this.oauthApiUrl + '/user-management-service/create', user);
  }

  public getUserCode(code: number) {
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.http.post(this.oauthApiUrl + '/user-management-service/activate/' + code, {headers: headers});
  }

  public getUserByEmail(email: string): Observable<User> {
    const params = new HttpParams().set('email', email);
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.httpClient.post<User>(this.oauthApiUrl + '/user-management-service/get/email', {headers: headers}, {params: params});
  }

  public isEmailExists(email: string): Observable<any> {
    const params = new HttpParams().set('email', email);
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.httpClient.post<boolean>(this.oauthApiUrl + '/user-management-service/get/email-check', {headers: headers}, {params: params});
  }

  public getRoleList(): Observable<Role> {
    return this.httpClient.get<Role>(this.oauthApiUrl + '/user-management-service/get/role-list', {responseType: 'json'});
  }


  public getRouteInfoForDriver(email: string) {
    const params = new HttpParams().set('email', email);
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.httpClient.get<RouteEntity>(this.oauthApiUrl + '/user-management-service/get/driver-route', {
      headers: headers,
      params: params,
      responseType: 'json'
    });
  }

  public getTransportInfoForDriver(email: string) {
    const params = new HttpParams().set('email', email);
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.httpClient.get<TransportEntity>(this.oauthApiUrl + '/user-management-service/get/driver-transport', {
      headers: headers,
      params: params,
      responseType: 'json'
    });
  }
}
