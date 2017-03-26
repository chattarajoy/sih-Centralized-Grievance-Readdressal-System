import { Component } from '@angular/core';
import { AppService } from '../services/app-services';
import { Router } from '@angular/router';


@Component({
  selector: 'logsig-component',
  templateUrl: './log-sig.component.html',
  styleUrls: ['./log-sig.component.css'],
  providers :[AppService]
})
export class LogSigComponent {
  //title = 'app works!';

  localuser = {
    emailId:'',
    password:''
  }

  signUpUser = {
    name : '',
    contact : '',
    emailId : '',
    password : ''

  }


  constructor(
    private _service : AppService,
    private _router : Router
  ){}

  login(){
    this._service.loginFun(this.localuser).then((res)=>{
      if(res){
        this._router.navigate(['user']);
        alert('logged in');
      }
      else if(res == false){
        alert('false!');
      }
    })
  }




  signUp(){
    this._service.signUpFun(this.signUpUser).then((res)=>{
      console.log('stuff',res);
      this._router.navigate(['']);
    })

  }

}
