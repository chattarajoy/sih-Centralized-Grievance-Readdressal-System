import { Component , OnInit } from '@angular/core';
import { UserFormComponent } from './userform/userform.component';
import { UserComplaintComponent } from './usercomplaints/usercomplaint.component';
import { AuthManager } from '../services/authmanager.service';
import { StatusLog } from '../login_signup/classes/status';
import { AppService } from '../services/app-services';
import { UserDetailX } from './classes/userdetails/userdetail';
import { NotificationsService } from 'angular2-notifications';
import { Router } from '@angular/router';
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

   data =  {
     user_name : '',
     phone : '',
     aadhar_verified : ''
   }

   constructor(
     private _service : AppService,
     private _serviceNotify: NotificationsService,
     private _router : Router
   ){}

  ngOnInit(){
   this.getDetails();
  }

  getDetails(){
    this._service.loginFun(this.localuser).then((res)=>{
    this.data.user_name = res.user_name;
    this.data.phone = res.phone_no_verified;
    this.data.aadhar_verified = res.aadhar_verified;
    if(res.aadhar_verified === false){
        //alert('Aadhar is not Verified!');
        alert('Aadhar is not verified');
        window.localStorage.setItem('aadhar_status',res.aadhar_verified);
        // this._router.navigate(['home']);
    }else if(res.aadhar_verified === true){
      console.log('verified');
        window.localStorage.setItem('aadhar_status',res.aadhar_verified);
    }
    //  window.localStorage.removeItem('a');
    //  window.localStorage.removeItem('b');
    })
  }

}
