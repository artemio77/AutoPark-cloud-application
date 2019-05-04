import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {User} from '../../model/user';
import {UserService} from '../../service/user.service';
import {AppService} from '../../service/app.service';
import {LoadingBarService} from '@ngx-loading-bar/core';
import {AppComponent} from '../app.component';
import {Router} from '@angular/router';
import {map} from 'rxjs/internal/operators';
import {Observable} from 'rxjs';
import {AuthService} from '../../service/auth.service';
import {MatDialog, MatSidenav} from '@angular/material';
import {UserSearchComponent} from './user-search/user-search.component';
import {RoleGuardService} from '../../service/role-guard.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  private user: User;
  private currentUser: Observable<User>;
  @Output() childEvent = new EventEmitter();
  @ViewChild('sidenav') sidenav: MatSidenav;

  constructor(private appService: AppService,
              private roleGuardService: RoleGuardService,
              private userService: UserService,
              private loader: LoadingBarService,
              private appComponent: AppComponent,
              private authService: AuthService,
              private _router: Router,
              public dialog: MatDialog) {


  }

  ngOnInit() {
    this.currentUser = this.authService.getAuthUser(localStorage.getItem('login')).pipe(map(data => this.user = data));
    this.currentUser.subscribe(data => this.user = data);
    if (this.roleGuardService.getRole() === 'ROLE_MANAGER') {
      this._router.navigate(['application/route', localStorage.getItem('login')]);
    } else {
      this._router.navigate(['application/view', localStorage.getItem('login')]);
    }
  }

  public getUser() {
    return this.currentUser;
  }

  searchContact() {
    const dialogRef = this.dialog.open(UserSearchComponent, {
      width: '850vh',
      height: '70vh'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  closeSideNav() {
    this.sidenav.close();
  }

  logout() {
    this.appService.logout();
  }
}
