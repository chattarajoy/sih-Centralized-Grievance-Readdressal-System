import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Rx';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class AuthServiceService {

   isLoggedIn : boolean;
   authKey : any;
   secKey : any;
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

    this._http.post(`http://54.169.134.133:80/auth/user_login?email=`+usercreds.emailId+`&password=`+usercreds.password,{headers:headers})
      .subscribe((data) =>{
        console.log('from auth-service',data.json());
        if(data.json().status === "success"){
          console.log('successCheck');
          window.localStorage.setItem('access_token',data.json().access_token);
          window.localStorage.setItem('secret_key',data.json().secret_key);

          this.isLoggedIn = true;
          resolve(this.isLoggedIn);
           var headers = new Headers();
           headers.append('access_token',data.json().access_token);
           headers.append('secret_key',data.json().secret_key);


      // this._http.get(`http://54.169.134.133:80/complaints/index`,{headers:headers})
      //
      //  .map( res => console.log('inside',res.json()));

        }
      })

    })



  }


  getStatusX(){
       return this._http.get(`http://54.169.134.133:80/complaint/index`,{headers:this.getHeaders()})
        .map( res => res.json());
    }

    private getHeaders(){
      let headers = new Headers();
       headers.append('access_token',window.localStorage.getItem('access_token'));
       headers.append('secret_key',window.localStorage.getItem('secret_key'));
      return headers;
    }









}
