
import { Component , OnInit} from '@angular/core';
import { AppAdminService } from '../../services/admin-service.service';
import { Router } from '@angular/router';
// import { StatusLog } from '../login_signup/classes/status';
// import { signUp } from '../login_signup/classes/signupuser';

import { NotificationsService } from 'angular2-notifications';

@Component({
  selector: 'admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css'],
  providers :[AppAdminService]
})
export class AdminLoginComponent implements OnInit {




  adminUser = {
    emailId:'',
    password:''
  }



  public options = {
    position: ["top", "right"],
    timeOut: 1000,
    lastOnBottom: true,
    showProgressBar : false
}






  constructor(
    private _service : AppAdminService,
    private _router : Router,
    private _notify : NotificationsService
  ){}


  ngOnInit(){


  }

  login(){
    this._service.loginFun(this.adminUser).then((res)=>{


      if(res.status === 'success'){
        //console.log()
        console.log('logged in ');
        this._router.navigate(['/admin']);
      }
      else if(res.status === 'error'){
            this._notify.error('ERROR',res.error_message);
            console.log('error');

      }
    })
  }




}
