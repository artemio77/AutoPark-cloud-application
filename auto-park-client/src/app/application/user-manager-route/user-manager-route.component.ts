import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {RouteEntity} from '../../../model/route';
import {RouteService} from '../../../service/route.service';
import {UserAssignComponent} from '../user-manager-transport/user-assign-component/user-assign.component';
import {animate, state, style, transition, trigger} from '@angular/animations';

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
  private dialog: MatDialog;
  public assignUserFilterChecked = false;
  public assignRouteFilterChecked = false;
  routeService: RouteService;
  expandedElement: RouteEntity | null;

  constructor(routeService: RouteService) {
    this.routeService = routeService;
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

}
