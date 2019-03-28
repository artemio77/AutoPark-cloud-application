import {Injectable} from '@angular/core';
import {environment} from '../environments/environment';
import {Http} from '@angular/http';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TransportEntity} from '../model/transport';
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
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    console.log(headers);
    return this.httpClient.get<RouteEntity[]>(this.oauthApiUrl + '/route-service/get/route', {headers: headers});
  }

}
