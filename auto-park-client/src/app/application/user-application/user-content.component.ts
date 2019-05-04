import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {LoadingBarService} from '@ngx-loading-bar/core';
import {AppService} from '../../../service/app.service';
import {UserService} from '../../../service/user.service';
import {Cookie} from 'ng2-cookies';
import {RouteEntity} from '../../../model/route';
import {TransportEntity} from '../../../model/transport';

@Component({
  selector: 'app-user-content',
  templateUrl: './user-content.component.html',
  styleUrls: ['./user-content.component.css']
})

export class UserContentComponent implements OnInit, OnDestroy {
  mode = new FormControl('over');
  urlPrefix = '/application/';
  urlSuffix = '/context';

  route: RouteEntity = new RouteEntity();
  transport: TransportEntity = new TransportEntity();

  constructor(private loader: LoadingBarService,
              private userService: UserService,
              private appService: AppService) {
  }

  ngOnInit() {
    this.getRoute();
    this.getTransport();
  }

  ngOnDestroy(): void {
  }


  getTransport() {
    this.userService.getTransportInfoForDriver(localStorage.getItem('login')).subscribe(value => {
      this.transport = value;
    });
  }

  getRoute() {
    this.userService.getRouteInfoForDriver(localStorage.getItem('login')).subscribe(value => {
      this.route = value;
    });
  }

  logout() {
    this.appService.logout();
  }

}
