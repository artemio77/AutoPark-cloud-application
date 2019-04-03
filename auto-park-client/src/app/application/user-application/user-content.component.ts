import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {LoadingBarService} from '@ngx-loading-bar/core';
import {AppService} from '../../../service/app.service';
import {UserService} from '../../../service/user.service';

@Component({
  selector: 'app-user-content',
  templateUrl: './user-content.component.html',
  styleUrls: ['./user-content.component.css']
})

export class UserContentComponent implements OnInit, OnDestroy {
  mode = new FormControl('over');
  urlPrefix = '/application/';
  urlSuffix = '/context';

  constructor(private loader: LoadingBarService,
              private userService: UserService,
              private appService: AppService) {
  }

  ngOnInit() {
  }

  ngOnDestroy(): void {
  }


  logout() {
    this.appService.logout();
  }

}
