import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class AppService {

  isLoggedIn : boolean;


  constructor(
    private _http: Http
  ) { }

 loginFun(usercreds){
   this.isLoggedIn = false;
   var headers = new Headers();
   var creds = 'email=' + usercreds.emailId + '&password'+ usercreds.password;
   headers.append('Content-type','application/x-www-form=urlencoded')

  return new Promise ((resolve) => {

  console.log(usercreds.emailId);
  console.log(usercreds.password);

   this._http.post(`http://54.169.134.133:80/auth/user_login?email=`+usercreds.emailId+`&password=`+usercreds.password,{headers:headers})
   .map( res => res.json())
     .subscribe((res) =>{
       console.log('from auth-service',res);

       if(res.status === "success"){
         console.log('successCheck');

         window.localStorage.setItem('access_token',res.access_token);
         window.localStorage.setItem('secret_key',res.secret_key);

         this.isLoggedIn = true;
         resolve(this.isLoggedIn);
     // this._http.get(`http://54.169.134.133:80/complaints/index`,{headers:headers})
     //
     //  .map( res => console.log('inside',res.json()));

       }
       else if(res.status === "error"){


       }
     })


   })

 }
//this._http.post(`http://54.169.134.133:80/user/signup?name=`+usercreds.name+`&contact=`+usercreds.contact+`&email=`+usercreds.emailId+`&password=`+usercreds.password,{headers:headers})
signUpFun(usercreds){

  var headers = new Headers();

  headers.append('Content-type','application/x-www-form=urlencoded')

 return new Promise ((resolve) => {


  this._http.post(`http://54.169.134.133:80/user/signup?name=`+usercreds.name+`&contact=`+usercreds.contact+`&email=`+usercreds.emailId+`&password=`+usercreds.password,{headers:headers})
  .map( res => res.json())
    .subscribe((res) =>{
      console.log('from auth-service',res);

      if(res.status === "success"){
        console.log('successCheck');

        // window.localStorage.setItem('access_token',res.access_token);
        // window.localStorage.setItem('secret_key',res.secret_key)

    // this._http.get(`http://54.169.134.133:80/complaints/index`,{headers:headers})
    //
    //  .map( res => console.log('inside',res.json()));

      }
    })


  })

}

getStatusX(){
     return this._http.get(`http://54.169.134.133:80/complaint/show_user_complaints`,{headers:this.getHeaders()})
      .map( res => res.json());
  }




  private getHeaders(){
    let headers = new Headers();
     headers.append('access_token',window.localStorage.getItem('access_token'));
     headers.append('secret_key',window.localStorage.getItem('secret_key'));
    return headers;
  }

}
