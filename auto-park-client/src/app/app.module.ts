import {
  MatAutocompleteModule,
  MatBadgeModule,
  MatBottomSheetModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatDividerModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatStepperModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatTreeModule,
} from '@angular/material';
import {CdkTableModule} from '@angular/cdk/table';
import {RegistrationSnackBarComponent} from './bars/registration/registration-snack-bar/registration-snack-bar.component';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {RegistrationComponent} from './loginRegistration/registration/registration.component';
import {LoginComponent} from './loginRegistration/login/login.component';
import {UserComponent} from './application/user.component';
import {AppRoutingModule} from '../modules/app-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Ng2CloudinaryModule} from 'ng2-cloudinary';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AuthService} from '../service/auth.service';
import {UserService} from '../service/user.service';
import {AppService} from '../service/app.service';
import {HttpModule} from '@angular/http';
import {LoginRegistrationComponent} from './loginRegistration/login.registration.component';
import {LoadingBarHttpModule} from '@ngx-loading-bar/http';
import {LoadingBarRouterModule} from '@ngx-loading-bar/router';
import {LoadingBarHttpClientModule} from '@ngx-loading-bar/http-client';
import {ProgressBarComponent} from './bars/progressbar/progress-bar.component';
import {UserContentComponent} from './application/user-application/user-content.component';
import {RouterModule} from '@angular/router';
import {UserSearchComponent} from './application/user-search/user-search.component';
import {UserNavComponent} from './application/user-nav/user-nav.component';
import {AuthGuardService} from '../service/auth-guard.service';
import {JwtHelperService} from '@auth0/angular-jwt';
import {AppGuardModule} from '../modules/app-guard.module';
import {UserManagerTransportComponent} from './application/user-manager-transport/user-manager-transport.component';
import {TransportService} from '../service/transport.service';
import {UserAssignComponent} from './application/user-manager-transport/user-assign-component/user-assign.component';
import {UserManagerRouteComponent} from './application/user-manager-route/user-manager-route.component';
import {UserManagerDriverComponent} from './application/user-manager-driver/user-manager-driver.component';
import {RouteService} from '../service/route.service';
import {UserManagerDriverCreateComponent} from './application/user-manager-driver/user-manager-driver-create/user-manager-driver-create.component';
import {UserManagerRouteCreateComponent} from './application/user-manager-route/user-manager-route-create/user-manager-route-create.component';
import {UserManagerTransportCreateComponent} from './application/user-manager-transport/user-manager-transport-create/user-manager-transport-create.component';


@NgModule({
  entryComponents: [
    UserSearchComponent,
    UserAssignComponent,
    UserManagerTransportCreateComponent,
    UserManagerDriverCreateComponent,
    UserManagerRouteCreateComponent,],
  declarations: [
    RegistrationSnackBarComponent,
    UserManagerTransportComponent,
    AppComponent,
    UserNavComponent,
    RegistrationComponent,
    LoginComponent,
    UserComponent,
    LoginRegistrationComponent,
    ProgressBarComponent,
    UserContentComponent,
    UserSearchComponent,
    UserAssignComponent,
    UserManagerRouteComponent,
    UserManagerDriverComponent,
    UserManagerDriverCreateComponent,
    UserManagerRouteCreateComponent,
    UserManagerTransportCreateComponent
  ],
  imports: [
    CdkTableModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatTreeModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng2CloudinaryModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    LoadingBarHttpModule,
    LoadingBarHttpClientModule,
    LoadingBarRouterModule,
    RouterModule,
    AppGuardModule,
    MatTableModule
  ],
  providers: [
    TransportService,
    JwtHelperService,
    AuthGuardService,
    AuthService,
    UserService,
    RouteService,
    AppService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
