import { Component } from '@angular/core';
import { AuthServiceService } from '../services/auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'loginPage',
  templateUrl: './login-component.html',
  styleUrls: ['./login-component.css'],
  providers : [AuthServiceService]
})
export class LoginComponent {

  localuser = {
     emailId:'',
     password:''
  }

  constructor(
    private _service: AuthServiceService,
    private _router : Router
  ){}

 login(){
   console.log('start');
   this._service.loginfn(this.localuser).then((res)=>{
     console.log('check');

     if(res)

     this._router.navigate(['dashboard']);
    //console.log('yes');
     else
     console.log('HHAAH');
   })
 }

 clearfields(){
   this.localuser.emailId ='';
   this.localuser.password= '';
 }

}
