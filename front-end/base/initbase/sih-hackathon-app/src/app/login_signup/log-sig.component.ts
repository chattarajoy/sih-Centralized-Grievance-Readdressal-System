import { Component , OnInit} from '@angular/core';
import { AppService } from '../services/app-services';
import { Router } from '@angular/router';
import { StatusLog } from '../login_signup/classes/status';
import { signUp } from '../login_signup/classes/signupuser';

@Component({
  selector: 'logsig-component',
  templateUrl: './log-sig.component.html',
  styleUrls: ['./log-sig.component.css'],
  providers :[AppService]
})
export class LogSigComponent implements OnInit {
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

  //  public signUpUser: signUp;

  data : StatusLog[];


  constructor(
    private _service : AppService,
    private _router : Router
  ){}


  ngOnInit(){

    // this.signUpUser = {
    //         name: '',
    //         contact:'',
    //         email: '',
    //         password: '',
    //         confirmPassword: ''
    //     }

  }

  login(){
    this._service.loginFun(this.localuser).then((res)=>{
 this.data = res.error_message
      console.log('1 data',this.data);
      if(res.status === 'success'){
console.log('2 res',this.data);
        this._router.navigate(['user']);
      }
      else if(res.status === 'error'){
            console.log('error');

      }
    })
  }




  signUp(){

      console.log('ok');
    this._service.signUpFun(this.signUpUser).then((res)=>{
      console.log('stuff',res);

    })


  }

}
