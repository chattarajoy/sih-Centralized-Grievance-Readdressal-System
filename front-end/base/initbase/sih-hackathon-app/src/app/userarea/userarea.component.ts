import { Component , OnInit } from '@angular/core';
import { UserFormComponent } from './userform/userform.component';
import { UserComplaintComponent } from './usercomplaints/usercomplaint.component';
import { AuthManager } from '../services/authmanager.service';
import { StatusLog } from '../login_signup/classes/status';
import { AppService } from '../services/app-services';
import { UserDetailX } from './classes/userdetails/userdetail';
import { NotificationsService } from 'angular2-notifications';
@Component({
  selector: 'userarea-component',
  templateUrl: './userarea.component.html',
  styleUrls: ['./userarea.component.css'],
  providers: [AuthManager,AppService]
})
export class UserComponent implements OnInit {

  localuser = {
    emailId:window.localStorage.getItem('a'),
    password:window.localStorage.getItem('b')
  }

   data : UserDetailX[];

   constructor(
     private _service : AppService,
     private _serviceNotify: NotificationsService
   ){}

  ngOnInit(){
    // this.getDetails();
  }

  getDetails(){
    this._service.loginFun(this.localuser).then((res)=>{
    this.data = res.user_name;
    console.log('get',this.data);
    //  window.localStorage.removeItem('a');
    //  window.localStorage.removeItem('b');
    })
  }

}
