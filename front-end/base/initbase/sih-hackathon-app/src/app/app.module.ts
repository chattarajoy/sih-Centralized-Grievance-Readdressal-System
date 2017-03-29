import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

//custom-components
import { NavbarComponent } from './navbar/navbar.component';
import { LogSigComponent } from './login_signup/log-sig.component';
import { UserComponent } from './userarea/userarea.component';
import { AdminComponent } from './admin/adminarea.component';
import { HomeComponent } from './homearea/home.component';
import { UserComplaintComponent } from './userarea/usercomplaints/usercomplaint.component';
import { UserFormComponent } from './userarea/userform/userform.component';
import { NavbarHomeComponent } from './navbar-home/navbar-home.component';
import { AfterSubComponent } from './afterSubmission/afterSub.component';
//custom-components

import { AppComponent } from './app.component';
import { routes } from './routes/app-routes';
import { RouterModule,Routes } from '@angular/router';

//services
import {AuthManager} from './services/authmanager.service';
import { AppService } from './services/app-services';
import { EqualValidator } from './services/equal-validator';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LogSigComponent,
    UserComponent,
    AdminComponent,
    HomeComponent,
    UserFormComponent,
    UserComplaintComponent,
    EqualValidator,
    NavbarHomeComponent,
    AfterSubComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(routes),
    ReactiveFormsModule
  ],
  providers: [AuthManager],
  bootstrap: [AppComponent]
})
export class AppModule { }
