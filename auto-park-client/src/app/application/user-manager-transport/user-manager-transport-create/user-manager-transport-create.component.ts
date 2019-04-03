import {Component, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {TransportService} from '../../../../service/transport.service';
import {TransportEntity} from '../../../../model/transport';

@Component({
  selector: 'app-user-manager-transport-create',
  templateUrl: './user-manager-transport-create.component.html',
  styleUrls: ['./user-manager-transport-create.component.css']
})
export class UserManagerTransportCreateComponent implements OnInit {

  private form: FormGroup;
  private transportService: TransportService;
  private transport: TransportEntity = new TransportEntity();

  constructor(transportService: TransportService) {
    this.transportService = transportService;
  }

  ngOnInit() {
  }

  createTransport() {
    this.transportService.createTransport(this.transport);
  }
}
