import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';
import { Status } from '../userarea/classes/complaints_class/complaints_status';
import {BehaviorSubject} from 'rxjs/Rx';
import { locationX  } from '../userarea/userform/location'
require('aws-sdk/dist/aws-sdk');


@Injectable()
export class AppAdminService {
  isLoggedIn : boolean;
  status : Observable<Status[]>;
  private _status: BehaviorSubject<Status[]>;
  private dataStore: {
    status: Status[]
  };


  constructor(
    private _http: Http
  ) {
    this.dataStore = { status: [] };
    this._status = <BehaviorSubject<Status[]>>new BehaviorSubject([]);
    this.status = this._status.asObservable();


  }

 loginFun(usercreds):Promise<any>{
   this.isLoggedIn = false;
   var headers = new Headers();
   var creds = 'email=' + usercreds.emailId + '&password'+ usercreds.password;
   headers.append('Content-type','application/x-www-form=urlencoded')
   window.localStorage.setItem('a',usercreds.emailId);
   window.localStorage.setItem('b',usercreds.password);
  return new Promise ((resolve) => {

  console.log(usercreds.emailId);
  console.log(usercreds.password);

   this._http.post(`http://54.169.134.133:80/auth/admin_login?email=`+usercreds.emailId+`&password=`+usercreds.password,{headers:headers})
   .map( res => res.json())
     .subscribe((res) =>{
       console.log('from auth-service',res);
       if(res.status === "success"){
         console.log('successCheck');
         window.localStorage.setItem('admin_access_token',res.access_token);
         window.localStorage.setItem('admin_secret_key',res.secret_key);
         window.localStorage.setItem('level',res.designation);
         resolve(res);
       }
       else if(res.status === "error"){

             resolve(res);
       }
     })


   })


 }
//this._http.post(`http://54.169.134.133:80/user/signup?name=`+usercreds.name+`&contact=`+usercreds.contact+`&email=`+usercreds.emailId+`&password=`+usercreds.password,{headers:headers})












}
