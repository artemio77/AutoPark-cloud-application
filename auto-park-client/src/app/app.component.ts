import {AfterContentChecked, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {AuthGuardService} from '../service/auth-guard.service';
import {RoleGuardService} from "../service/role-guard.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterContentChecked {

  private check;

  constructor(private authGuardService: RoleGuardService,
              private changeDedectionRef: ChangeDetectorRef) {
  }

  ngOnInit() {
    this.check = this.authGuardService.isAuthenticated();
  }

  ngAfterContentChecked(): void {
    this.changeDedectionRef.detectChanges();
  }
}

