import {Injectable} from '@angular/core';
import {environment} from '../environments/environment';
import {Http} from '@angular/http';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Cookie} from 'ng2-cookies';
import {Observable} from 'rxjs';
import {TransportEntity} from '../model/transport';
import {TransportType} from '../model/create/transport.type';

@Injectable()
export class TransportService {


  private oauthApiUrl = environment.apiUrl;

  constructor(private http: Http,
              private httpClient: HttpClient) {
  }

  public getTransportList(): Observable<TransportEntity[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    console.log(headers);
    return this.httpClient.get<TransportEntity[]>(this.oauthApiUrl + '/transport-service/get/transport', {headers: headers});
  }

  public getTypeList(): Observable<TransportType> {
    return this.httpClient.get<TransportType>(this.oauthApiUrl + '/transport-service/get/type-list', {responseType: 'json'});
  }

  public getTransportWithoutDriver(): Observable<TransportEntity[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    console.log(headers);
    return this.httpClient.get<TransportEntity[]>(this.oauthApiUrl + '/transport-service/get/empty-user', {headers: headers});
  }

  public getTransportWithDriver(): Observable<TransportEntity[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    console.log(headers);
    return this.httpClient.get<TransportEntity[]>(this.oauthApiUrl + '/transport-service/get/with-drivers', {headers: headers});
  }

  public getTransportWithRoute(): Observable<TransportEntity[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    console.log(headers);
    return this.httpClient.get<TransportEntity[]>(this.oauthApiUrl + '/transport-service/get/with-route', {headers: headers});
  }

  public getTransportWithOutRoute(): Observable<TransportEntity[]> {
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    console.log(headers);
    return this.httpClient.get<TransportEntity[]>(this.oauthApiUrl + '/transport-service/get/empty-route', {headers: headers});
  }

  public createTransport(transport) {
    return this.httpClient.post(this.oauthApiUrl + '/transport-service/create', transport);
  }

  public assignDriverInTransport(email, id) {
    const params = new HttpParams()
      .set('email', email)
      .set('id', id);
    const headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    });
    return this.httpClient.patch(this.oauthApiUrl + '/transport-service/assign-driver', {headers: headers}, {params: params});
  }

}
