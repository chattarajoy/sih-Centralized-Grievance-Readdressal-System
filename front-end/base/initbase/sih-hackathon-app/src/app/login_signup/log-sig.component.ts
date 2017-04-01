import { Component , OnInit} from '@angular/core';
import { AppService } from '../services/app-services';
import { Router } from '@angular/router';
import { StatusLog } from '../login_signup/classes/status';
import { signUp } from '../login_signup/classes/signupuser';
import {ValidationManager} from "ng2-validation-manager";
import { NotificationsService } from 'angular2-notifications';

@Component({
  selector: 'logsig-component',
  templateUrl: './log-sig.component.html',
  styleUrls: ['./log-sig.component.css'],
  providers :[AppService]
})
export class LogSigComponent implements OnInit {
  //title = 'app works!';

  form;

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

  public options = {
    position: ["top", "right"],
    timeOut: 1000,
    lastOnBottom: true,
    showProgressBar : false
}

  //  public signUpUser: signUp;

  data : StatusLog[];


  constructor(
    private _service : AppService,
    private _router : Router,
    private _notify : NotificationsService
  ){}


  ngOnInit(){

    // this.signUpUser = {
    //         name: '',
    //         contact:'',
    //         email: '',
    //         password: '',
    //         confirmPassword: ''
    //     }

    this.form = new ValidationManager({
      'name'        : 'required|minLength:4|maxLength:12|alphaSpace',
      'contact'       :'required|minLength:10|maxLength:10',
      'email'       : 'required|email',
      'password'    : 'required|rangeLength:8,50',
      'repassword'  : 'required|equalTo:password'
    });

     this.form.setValue('name', 'Default');

  }

  login(){
    this._service.loginFun(this.localuser).then((res)=>{
 this.data = res.error_message

      if(res.status === 'success'){
        this._router.navigate(['user']);
      }
      else if(res.status === 'error'){
            this._notify.error('ERROR',res.error_message);
            console.log('error');

      }
    })
  }




  signUp(){

    if(this.form.isValid()){
      console.log('ok');
    this._service.signUpFun(this.signUpUser).then((res)=>{
      if(res.status === 'success'){
        alert('created account , login using credentials');
      }
      else if(res.status === 'error'){
            this._notify.error('ERROR',res.error_message);
            console.log('error');

      }
    })
       }


  }



}
