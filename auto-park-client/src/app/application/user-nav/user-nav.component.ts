import {AfterContentChecked, AfterContentInit, AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {AppService} from '../../../service/app.service';
import {User} from '../../../model/user';
import {UserComponent} from '../user.component';
import {Observable} from 'rxjs';
import {RoleGuardService} from '../../../service/role-guard.service';
import {ChangeDetectorRef} from '@angular/core';

@Component({
  selector: 'app-user-nav',
  templateUrl: './user-nav.component.html',
  styleUrls: ['./user-nav.component.css']
})
export class UserNavComponent implements OnInit {
  @Input()
  public user = new User;
  private currentUser: Observable<User>;
  private routeOnTransportUrl: string = '/application/transport/' + localStorage.getItem('login');
  private routeOnRouteUrl: string = '/application/route/' + localStorage.getItem('login');
  private routeOnDriversUrl: string = '/application/drivers/' + localStorage.getItem('login');


  constructor(private cdRef: ChangeDetectorRef,
              private roleGuardService: RoleGuardService,
              public userComponent: UserComponent,
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


