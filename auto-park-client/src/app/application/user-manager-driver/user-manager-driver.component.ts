import {Component, OnInit, ViewChild} from '@angular/core';
import {TransportEntity} from '../../../model/transport';
import {MatDialog, MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {UserService} from '../../../service/user.service';
import {User} from '../../../model/user';
import {UserManagerTransportCreateComponent} from '../user-manager-transport/user-manager-transport-create/user-manager-transport-create.component';
import {UserManagerDriverCreateComponent} from './user-manager-driver-create/user-manager-driver-create.component';

@Component({
  selector: 'app-user-manager-driver',
  templateUrl: './user-manager-driver.component.html',
  styleUrls: ['./user-manager-driver.component.css']
})
export class UserManagerDriverComponent implements OnInit {

  public displayedColumns = ['id', 'email', 'firstName', 'lastName', 'role', 'creationTime', 'modificationTime', 'isEnabled'];
  public dataSource = new MatTableDataSource<User>();
  private tableArray: User[];
  private dialog: MatDialog;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  public driverFilterChecked = false;
  public managerFilterChecked = false;

  constructor(private userService: UserService, private matDialog: MatDialog) {
    this.dialog = matDialog;
  }

  ngOnInit() {
    this.createTable();
  }

  createTable() {
    this.userService.getAllUserList()
      .subscribe(res => {
        this.dataSource.data = res as User[];
      });
    this.dataSource = new MatTableDataSource(this.tableArray);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.driverFilterChecked = false;
    this.managerFilterChecked = false;
  }

  public createUser() {
    const dialogRef = this.dialog.open(UserManagerDriverCreateComponent, {
      width: '850vh',
      height: '70vh',
    });
    dialogRef.afterClosed().subscribe(result => {
      this.createTable();
    });
  }
}
