import {Component, OnInit} from '@angular/core';
import {AuthGuardService} from '../service/auth-guard.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {


  constructor(private authGuardService: AuthGuardService) {
  }

  ngOnInit() {
  }

}

