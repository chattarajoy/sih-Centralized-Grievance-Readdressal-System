import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

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
    headers.append('Content-type','application/X-www-form=urlencoded')

   return new Promise ((resolve) => {



    this._http.post('http://192.168.117.60.:8000/auth/user_login?',creds,{headers:headers})
      .subscribe((data) =>{
        if(data.json().success){
          window.localStorage.setItem('auth_key',data.json().token);
          this.isLoggedIn = true;
          resolve(this.isLoggedIn);
        }
      })

    })
  }



}
