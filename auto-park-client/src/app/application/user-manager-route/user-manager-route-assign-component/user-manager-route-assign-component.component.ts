import {Component, Inject, OnInit} from '@angular/core';
import {TransportService} from '../../../../service/transport.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {DialogData} from '../../user-manager-transport/user-assign-component/user-assign.component';
import {TransportEntity} from '../../../../model/transport';
import {RouteService} from '../../../../service/route.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-manager-route-assign-component',
  templateUrl: './user-manager-route-assign-component.component.html',
  styleUrls: ['./user-manager-route-assign-component.component.css']
})
export class UserManagerRouteAssignComponentComponent implements OnInit {

  private transportList: TransportEntity[];
  disableSelect = false;
  selectedRouteId;

  constructor(private router: Router,
              private routeService: RouteService,
              private  transportService: TransportService,
              public dialogRef: MatDialogRef<UserManagerRouteAssignComponentComponent>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }


  ngOnInit() {
    this.getTransportWithoutRoute();
  }

  onNoClick(): void {
    this.dialogRef.close();
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

  assignTransport(transportId, routeId) {
    this.routeService.assignTransportOnRoute(transportId, routeId).subscribe();
    this.onNoClick();

  }
}
