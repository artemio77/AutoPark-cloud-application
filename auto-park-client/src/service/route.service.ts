import {Injectable} from '@angular/core';
import {environment} from '../environments/environment';
import {Http} from '@angular/http';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Cookie} from 'ng2-cookies';
import {RouteEntity} from '../model/route';

@Injectable()
export class RouteService {


  private oauthApiUrl = environment.apiUrl;

  constructor(private http: Http,
              private httpClient: HttpClient) {
  }

  public getRouteList(): Observable<RouteEntity[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    console.log(headers);
    return this.httpClient.get<RouteEntity[]>(this.oauthApiUrl + '/route-service/get/route', {headers: headers});
  }

  public getRouteListWithTransport(): Observable<RouteEntity[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    console.log(headers);
    return this.httpClient.get<RouteEntity[]>(this.oauthApiUrl + '/route-service/get/route-transport', {headers: headers});
  }

  public getRouteListWithDrivers(): Observable<RouteEntity[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    console.log(headers);
    return this.httpClient.get<RouteEntity[]>(this.oauthApiUrl + '/route-service/get/route-with-drivers', {headers: headers});
  }

  public createRoute(route) {
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    console.log(headers);
    return this.httpClient.post<RouteEntity>(this.oauthApiUrl + '/route-service/create', route);
  }

  public getRoute(routeId) {
    const params = new HttpParams()
      .set('routeId', routeId);
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    console.log(headers);
    return this.httpClient.get<RouteEntity>(this.oauthApiUrl + '/route-service/get', {params: params});
  }

  public assignTransportOnRoute(transportId, routeId) {
    const params = new HttpParams()
      .set('transportId', transportId)
      .set('routeId', routeId);
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.httpClient.patch(this.oauthApiUrl + '/route-service/assign-transport', {headers: headers}, {params: params});
  }

  public removeAssignTransportOnRoute(transportId, routeId): Observable<RouteEntity> {
    const params = new HttpParams()
      .set('transportId', transportId)
      .set('routeId', routeId);
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.httpClient.patch<RouteEntity>(this.oauthApiUrl + '/route-service/remove-assign-transport', {headers: headers}, {params: params});
  }


}
