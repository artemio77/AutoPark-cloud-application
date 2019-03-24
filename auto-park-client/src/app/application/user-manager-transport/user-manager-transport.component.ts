import {Component, OnInit, ViewChild} from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {TransportService} from '../../../service/transport.service';
import {MatDialog, MatPaginator, MatSlideToggleChange, MatSort, MatTableDataSource} from '@angular/material';
import {TransportEntity} from '../../../model/transport';
import {UserAssignComponent} from '../user-assign-component/user-assign.component';

@Component({
  selector: 'app-user-manager-transport',
  templateUrl: './user-manager-transport.component.html',
  styleUrls: ['./user-manager-transport.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0', display: 'none'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class UserManagerTransportComponent implements OnInit {

  public displayedColumns = ['id', 'name', 'transportType', 'currentAssignUser', 'currentRouteAssign', 'numberPlate', 'action'];
  public dataSource = new MatTableDataSource<TransportEntity>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  transportService: TransportService;
  private tableArray: TransportEntity[];
  private dialog: MatDialog;
  public assignUserFilterChecked = false;
  public assignRouteFilterChecked = false;

  constructor(transportService: TransportService, private matDialog: MatDialog) {
    this.transportService = transportService;
    this.dialog = matDialog;
  }

  ngOnInit() {
    this.createTable();
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  createTable() {
    this.transportService.getTransportList()
      .subscribe(res => {
        this.dataSource.data = res as TransportEntity[];
      });
    this.dataSource = new MatTableDataSource(this.tableArray);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.assignRouteFilterChecked = false;
    this.assignUserFilterChecked = false;
  }


  public assignOnRoute(id: string, name: string) {
    const dialogRef = this.dialog.open(UserAssignComponent, {
      width: '850vh',
      height: '70vh',
      data: {id: id, name: name}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  public toggleAssignUserFilter(event: MatSlideToggleChange) {
    if (!this.assignUserFilterChecked) {
      this.transportService.getTransportWithDriver()
        .subscribe(res => {
          this.dataSource.data = res as TransportEntity[];
        });
      this.assignRouteFilterChecked = false;
      this.dataSource = new MatTableDataSource(this.tableArray);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.assignUserFilterChecked = true;
    } else {
      this.createTable();
    }
  }

  public toggleAssignRouteFilter(event: MatSlideToggleChange) {
    if (!this.assignRouteFilterChecked) {
      this.transportService.getTransportWithRoute()
        .subscribe(res => {
          this.dataSource.data = res as TransportEntity[];
        });
      this.assignUserFilterChecked = false;
      this.dataSource = new MatTableDataSource(this.tableArray);
      this.assignRouteFilterChecked = true;
    } else {
      this.createTable();
    }
  }
}

