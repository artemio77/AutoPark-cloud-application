import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroupDirective, NgForm} from '@angular/forms';
import {UserService} from '../../../service/user.service';
import {ErrorStateMatcher, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {User} from '../../../model/user';
import {TransportService} from '../../../service/transport.service';

export interface DialogData {
  id: string;
  name: string;
}

@Component({
  selector: 'app-user-assign-component',
  templateUrl: './user-assign.component.html',
  styleUrls: ['./user-assign.component.css']
})
export class UserAssignComponent implements OnInit {

  selectedUserEmail: string;
  private email;
  private userArray: User[];
  disableSelect = false;

  constructor(private userService: UserService,
              private  transportService: TransportService,
              public dialogRef: MatDialogRef<UserAssignComponent>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    this.getDrivers();
  }

  public search() {
    this.userService.getUserByEmail(this.email);
  }

  getDrivers() {
    this.userService.getDrivers();
    this.userService.getDrivers().subscribe(response => {
      this.userArray = response;
    });
    if (typeof this.userArray !== 'undefined' && this.userArray.length > 0) {
      this.disableSelect = true;
    }
  }

  assignUser(email, id) {
    console.log(email);
    this.transportService.assignDriverInTransport(email, id).subscribe();
  }

}

export class SearchErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
