import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';
import { Status } from '../userarea/classes/complaints_class/complaints_status';
import {BehaviorSubject} from 'rxjs/Rx';

@Injectable()
export class AppService {

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

   this._http.post(`http://54.169.134.133:80/auth/user_login?email=`+usercreds.emailId+`&password=`+usercreds.password,{headers:headers})
   .map( res => res.json())
     .subscribe((res) =>{
       console.log('from auth-service',res);
       if(res.status === "success"){
         console.log('successCheck');
         window.localStorage.setItem('access_token',res.access_token);
         window.localStorage.setItem('secret_key',res.secret_key);
         resolve(res);
       }
       else if(res.status === "error"){

             resolve(res);
       }
     })


   })


 }
//this._http.post(`http://54.169.134.133:80/user/signup?name=`+usercreds.name+`&contact=`+usercreds.contact+`&email=`+usercreds.emailId+`&password=`+usercreds.password,{headers:headers})
signUpFun(usercreds){

  var headers = new Headers();

  headers.append('Content-type','application/x-www-form=urlencoded')
  headers.append('check','check')
console.log('reached',usercreds);
 return new Promise ((resolve) => {
  this._http.post(`http://54.169.134.133:80/user/signup?name=`+usercreds.name+`&contact=`+usercreds.contact+`&email=`+usercreds.emailId+`&password=`+usercreds.password,{headers:headers})
  .map( res => res.json())
    .subscribe((res) =>{
      if(res.status === "success"){
        console.log('successCheck');

    resolve(res);

      }
    })


  })

}

getStatusX(){
 this._http.get(`http://54.169.134.133:80/complaint/show_user_complaints`,{headers:this.getHeaders()})
      .map( res => res.json()).subscribe(res => {
        this.dataStore.status = res;
        this._status.next(Object.assign({},this.dataStore).status);
      },error => console.log('oops')
    );

  }

  // loadAll() {
  //     this.http.get(`${this.baseUrl}/todos`).map(response => response.json()).subscribe(data => {
  //       this.dataStore.todos = data;
  //       this._todos.next(Object.assign({}, this.dataStore).todos);
  //     }, error => console.log('Could not load todos.'));
  //   }




  private getHeaders(){
    let headers = new Headers();

     //headers.append('Access-Control-Allow-Methods','GET, POST, OPTIONS');
     headers.append('Content-type','form-data');
     headers.append('access_token',window.localStorage.getItem('access_token'));
     headers.append('secret_key',window.localStorage.getItem('secret_key'));
     window.localStorage.setItem('intercepted_access_token',window.localStorage.getItem('access_token'));
      window.localStorage.setItem('intercepted_secret_key',window.localStorage.getItem('secret_key'));
     console.log('headers',headers);
    return headers;
  }


}


////
