import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Rx';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class AuthServiceService {

   isLoggedIn : boolean;

   //url : '192.168.117.60:8000/auth/user_login?email=xyz@xyz.com&password=tiru';

  constructor(
    private _http: Http
  ) { }

  loginfn(usercreds){

    this.isLoggedIn = false;
    var headers = new Headers();
    var creds = 'email=' + usercreds.emailId + '&password'+ usercreds.password;
    headers.append('Content-type','application/x-www-form=urlencoded')

   return new Promise ((resolve) => {

   console.log(usercreds.emailId);
   console.log(usercreds.password);

    this._http.post(`http://192.168.117.60.:8000/auth/user_login?email=`+usercreds.emailId+`&password=`+usercreds.password,{headers:headers})
      .subscribe((data) =>{
        console.log('from auth-service',data.json());
        if(data.json().status === "success"){
          console.log('successCheck');
          window.localStorage.setItem('access_token',data.json().token);
          window.localStorage.setItem('secret_key',data.json().token);
          this.isLoggedIn = true;
          resolve(this.isLoggedIn);
      //      var headers = new Headers();
      //      headers.append('access_token',data.json().token);
      //      headers.append('secret_key',data.json().token);
      //
      // this._http.get(`http://192.168.117.60:8000/complaints/index`,{headers:headers})
      //
      //  .map( res => console.log('inside',res.json()));

        }
      })

    })



  }


getStatusX(){
  return this._http.get(`http://192.168.117.94:5000/users`,{headers:headers})
       .map( res => res.json());
}










}
