import {Component, OnInit} from '@angular/core';
import {Route} from '@angular/router';
import {RouteService} from '../../../../service/route.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDialogRef} from '@angular/material';
import {TransportService} from '../../../../service/transport.service';
import {TransportEntity} from '../../../../model/transport';
import {RouteEntityCreate} from '../../../../model/create/route.create';

@Component({
  selector: 'app-user-manager-route-create',
  templateUrl: './user-manager-route-create.component.html',
  styleUrls: ['./user-manager-route-create.component.css']
})
export class UserManagerRouteCreateComponent implements OnInit {

  route: RouteEntityCreate = new RouteEntityCreate();

  transportList: TransportEntity[];

  form: FormGroup;

  routeNumberFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('[0-9]{10}')
  ]);
  nameFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[a-zA-Z_0-9]*$'),
    Validators.min(2),
    Validators.max(30)
  ]);

  disableSelect = false;
  selectedTransportId;

  constructor(
    private transportService: TransportService,
    private routeService: RouteService,
    public dialogRef: MatDialogRef<UserManagerRouteCreateComponent>,
    private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      name: ['', [Validators.required,
        Validators.pattern('^[a-zA-Z_0-9]*$'),
        Validators.min(2),
        Validators.max(30)]],
      routeNumber: ['', [Validators.required,
        Validators.pattern('[0-9]*$'),
        Validators.maxLength(10),
        Validators.minLength(1)]],
      transport: ['', []]
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


  ngOnInit() {
    this.getTransportWithoutRoute();
  }

  createRoute() {
    console.log('Route ' + JSON.stringify(this.route));
    this.routeService.createRoute(this.route).subscribe();
    this.onNoClick();
  }


  getTransportWithoutRoute() {
    this.transportService.getTransportWithOutRoute().subscribe(response => {
      this.transportList = response;
      console.log('Avaliable Transport ' + JSON.stringify(this.transportList));
    });
    if (typeof this.transportList !== 'undefined' && this.transportList.length > 0) {
      this.disableSelect = true;
    }

  }
}
