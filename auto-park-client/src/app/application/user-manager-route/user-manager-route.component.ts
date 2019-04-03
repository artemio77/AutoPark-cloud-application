import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatPaginator, MatSlideToggleChange, MatSort, MatTableDataSource} from '@angular/material';
import {RouteEntity} from '../../../model/route';
import {RouteService} from '../../../service/route.service';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {UserManagerRouteCreateComponent} from './user-manager-route-create/user-manager-route-create.component';
import {UserManagerRouteAssignComponentComponent} from './user-manager-route-assign-component/user-manager-route-assign-component.component';
import {TransportEntity} from '../../../model/transport';
import {Cookie} from 'ng2-cookies';

@Component({
  selector: 'app-user-manager-route',
  templateUrl: './user-manager-route.component.html',
  styleUrls: ['./user-manager-route.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0', display: 'none'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class UserManagerRouteComponent implements OnInit {

  public displayedColumns = ['id', 'name', 'routeNumber', 'action'];
  public dataSource = new MatTableDataSource<RouteEntity>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  private tableArray: RouteEntity[];
  public assignRouteFilterChecked = false;
  public assignRouteUserFilterChecked = false;
  routeService: RouteService;
  expandedElement: RouteEntity | null;
  dialog: MatDialog;
  route: RouteEntity = new RouteEntity();


  constructor(routeService: RouteService, private matDialog: MatDialog) {
    this.routeService = routeService;
    this.dialog = matDialog;
  }

  ngOnInit() {
    this.createTable();
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  createTable() {
    this.routeService.getRouteList()
      .subscribe(res => {
        this.dataSource.data = res as RouteEntity[];
      });
    this.dataSource = new MatTableDataSource(this.tableArray);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.assignRouteFilterChecked = false;
    return this.dataSource;
  }


  public assignOnRoute(id: string, name: string) {
    const dialogRef = this.dialog.open(UserManagerRouteAssignComponentComponent, {
      width: '850vh',
      height: '70vh',
      data: {id: id, name: name}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.createTable();
    });
  }

  public createRoute() {
    const dialogRef = this.dialog.open(UserManagerRouteCreateComponent, {
      width: '850vh',
      height: '70vh',
    });
    dialogRef.afterClosed().subscribe(result => {
      this.createTable();
      this.dataSource._updateChangeSubscription();
    });
  }

  toggleAssignRouteFilter(event: MatSlideToggleChange) {
    if (!this.assignRouteFilterChecked) {
      this.routeService.getRouteListWithTransport()
        .subscribe(res => {
          this.dataSource.data = res as RouteEntity[];
        });
      this.assignRouteFilterChecked = true;
      this.dataSource = new MatTableDataSource(this.tableArray);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    } else {
      this.assignRouteUserFilterChecked = false;
      this.assignRouteFilterChecked = false;
      this.createTable();
    }
  }

  toggleAssignRouteFilterWithDrivers(event: MatSlideToggleChange) {
    if (!this.assignRouteUserFilterChecked) {
      this.routeService.getRouteListWithDrivers()
        .subscribe(res => {
          this.dataSource.data = res as RouteEntity[];
        });
      this.assignRouteUserFilterChecked = true;
      this.dataSource = new MatTableDataSource(this.tableArray);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    } else {
      this.assignRouteUserFilterChecked = false;
      this.createTable();
    }
  }

  removeAssignOnRoute(transportId, routeId) {
    this.routeService.removeAssignTransportOnRoute(transportId, routeId).subscribe(
      response => {
        this.createTable();
        this.dataSource._updateChangeSubscription();
      });
  }
}
