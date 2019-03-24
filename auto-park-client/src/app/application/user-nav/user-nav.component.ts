import {Component, Input, OnInit} from '@angular/core';
import {AppService} from '../../../service/app.service';
import {User} from '../../../model/user';
import {UserComponent} from '../user.component';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-user-nav',
  templateUrl: './user-nav.component.html',
  styleUrls: ['./user-nav.component.css']
})
export class UserNavComponent implements OnInit {
  @Input()
  public user = new User;
  private currentUser: Observable<User>;
  private routeOnTransportUrl: string = '/application/' + localStorage.getItem('login') + '/transport';
  private routeOnRouteUrl: string = '/application/' + localStorage.getItem('login') + '/route';
  private routeOnDriversUrl: string = '/application/' + localStorage.getItem('login') + '/drivers';


  constructor(private userComponent: UserComponent,
              private appService: AppService) {

  }

  ngOnInit() {
    this.currentUser = this.userComponent.getUser();
    this.currentUser.subscribe(data => this.user = data);
  }

  logout() {
    this.appService.logout();
  }
}


