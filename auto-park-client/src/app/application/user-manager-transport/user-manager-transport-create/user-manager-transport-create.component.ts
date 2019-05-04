import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDialogRef} from '@angular/material';
import {User} from '../../../../model/user';
import {UserService} from '../../../../service/user.service';
import {TransportEntityCreate} from '../../../../model/create/transport.create';
import {TransportService} from '../../../../service/transport.service';
import {TransportType} from '../../../../model/create/transport.type';

@Component({
  selector: 'app-user-manager-transport-create',
  templateUrl: './user-manager-transport-create.component.html',
  styleUrls: ['./user-manager-transport-create.component.css']
})
export class UserManagerTransportCreateComponent implements OnInit {

  private userArray: User[];
  transportType: TransportType = new TransportType();
  disableSelectUser = false;
  disableSelectTransportType = false;
  private transport: TransportEntityCreate = new TransportEntityCreate();


  numberPlateFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('[a-zA-Z_][0-9]{8}')
  ]);
  nameFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[a-zA-Z_]*$'),
    Validators.min(2),
    Validators.max(30)
  ]);

  form: FormGroup;

  constructor(private transportService: TransportService,
              private userService: UserService,
              public dialogRef: MatDialogRef<UserManagerTransportCreateComponent>,
              private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      name: ['', [Validators.required,
        Validators.pattern('^[a-zA-Z_]*$'),
        Validators.min(2),
        Validators.max(30)]],
      numberPlate: ['', [Validators.required,
        Validators.pattern('^[a-zA-Z0-9]*$'),
        Validators.min(2),
        Validators.max(30)]],
      email: ['', []],
      transportType: ['', [Validators.required]]

    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit() {
    this.getDrivers();
    this.getTypeList();
  }

  getDrivers() {
    this.userService.getDrivers().subscribe(response => {
      this.userArray = response;
    });
    if (typeof this.userArray !== 'undefined' && this.userArray.length > 0) {
      this.disableSelectUser = true;
    }
  }

  getTypeList() {
    this.transportService.getTypeList()
      .subscribe(response => {
        this.transportType = response;
        console.log(this.transportType.value);
      });
    if (typeof this.transportType.value !== 'undefined' && this.transportType.value.length > 0) {
      this.disableSelectTransportType = true;
    }

  }


  createTransport() {
    console.log('Transport ' + JSON.stringify(this.transport));
    this.transportService.createTransport(this.transport).subscribe();
    this.dialogRef.close();
  }
}


