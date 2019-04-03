import {AfterContentChecked, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {AuthGuardService} from '../service/auth-guard.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterContentChecked {


  constructor(private authGuardService: AuthGuardService,
              private changeDedectionRef: ChangeDetectorRef) {
  }

  ngOnInit() {
  }

  ngAfterContentChecked(): void {
    this.changeDedectionRef.detectChanges();
  }
}

