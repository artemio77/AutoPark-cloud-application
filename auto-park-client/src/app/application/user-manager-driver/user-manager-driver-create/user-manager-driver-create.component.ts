import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {User} from '../../../../model/user';
import {AuthService} from '../../../../service/auth.service';
import {UserService} from '../../../../service/user.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {map} from 'rxjs/operators';
import {Role} from '../../../../model/create/role.type';
import {MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-user-manager-driver-create',
  templateUrl: './user-manager-driver-create.component.html',
  styleUrls: ['./user-manager-driver-create.component.css']
})
export class UserManagerDriverCreateComponent implements OnInit {

  private form: FormGroup;
  private user: User = new User();
  private role: Role = new Role();
  private disableSelectRole = false;

  firstNameFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[a-zA-Z_]*$'),
    Validators.min(2),
    Validators.max(30)
  ]);
  surnameFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[a-zA-Z_]*$'),
    Validators.min(2),
    Validators.max(30)
  ]);
  emailFormControl = new FormControl('', [
      Validators.required,
      Validators.email],
    this.emailExists.bind(this)
  );

  passwordFormControl = new FormControl('', [
    Validators.min(5),
    Validators.required]
  );

  constructor(public dialogRef: MatDialogRef<UserManagerDriverCreateComponent>,
              private auth: AuthService, private userService: UserService,
              private http: HttpClient, private router: Router,
              private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      firstName: ['', [Validators.required,
        Validators.pattern('^[a-zA-Z_]*$'),
        Validators.min(2),
        Validators.max(30)]],
      lastName: ['', [Validators.required,
        Validators.pattern('^[a-zA-Z_]*$'),
        Validators.min(2),
        Validators.max(30)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9]*$')]],
      role: ['', [Validators.required]],
    });
  }

  ngOnInit() {
    this.getRoleList();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


  emailExists(control: AbstractControl) {
    return this.userService.isEmailExists(control.value).pipe(map(response => {
      console.log(response);
      return response ? {exists: true} : null;
    }));

  }


  getRoleList() {
    this.userService.getRoleList()
      .subscribe(response => {
        this.role = response;
      });
    if (typeof this.role.value !== 'undefined' && this.role.value.length > 0) {
      this.disableSelectRole = true;
    }
  }


  registration() {
    this.auth.registration(this.user).subscribe(response => {
      this.form.reset();
    }, error => {
    });
    this.onNoClick();
  }
}
