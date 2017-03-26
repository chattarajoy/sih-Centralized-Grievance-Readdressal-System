import { Component , Input} from '@angular/core';
import { AuthServiceService } from '../services/auth-service.service';
import { Router } from '@angular/router';
import { StatusLog} from '../loginStat';
import { localuser } from '../localUser';
import { Status } from '../status';
import { DashboardComponent} from '../dashboard/dashboard.component';
@Component({
  selector: 'loginPage',
  templateUrl: './login-component.html',
  styleUrls: ['./login-component.css'],
  providers : [AuthServiceService]
})
export class LoginComponent {

  someString : string = "LOL";

  localuser = {
     emailId:'',
     password:''
  }

 status : StatusLog[];

  constructor(
    private _service: AuthServiceService,
    private _router : Router

  ){

  }

 login(){
   console.log('start');


   this._service.loginfn(this.localuser).then((res)=>{

     console.log('stuff',res);
     if(res){
       console.log('this is',res);
     this._router.navigate(['dashboard']);
  }
     else if(!res){
       console.log(this.status);
     console.log('HHAAH');

   }
 }).then((res)=>{
   console.log('something',res);
 })

 }
 // getUserDetails(){
 //   console.log('start');
 //
 //
 //   this._service.loginfn(this.localuser).then((res)=>{
 //
 //     console.log('check');
 //     console.log(this.status);
 //     if(res){
 //       console.log('this is',res);
 //     this._router.navigate(['dashboard']);
 //  }
 //     else if(!res){
 //       console.log(this.status);
 //     console.log('HHAAH');
 //
 //   }
 //   })
 //
 // }


 clearfields(){
   this.localuser.emailId ='';
   this.localuser.password= '';
 }

}
