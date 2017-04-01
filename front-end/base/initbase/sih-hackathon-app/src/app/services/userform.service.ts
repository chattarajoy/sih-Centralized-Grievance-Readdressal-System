import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class UserformService {

  constructor(
    private _http : Http
  ) { }

  getLocation(parameters):Observable<any>{
    console.log(parameters);
    var headers = new Headers();
    var key = 'AIzaSyBJSQWoYevjqqNZQmTrkNCUnZkDW656cbs';
    headers.append('access-control-expose-headers','*');
    headers.append('Access-Control-Allow-Origin', 'http://127.0.0.1:4200');
headers.append('Access-Control-Allow-Headers', 'Content-Type,Accept');
headers.append('Access-Control-Allow-Methods', 'POST');
console.log(headers);
    return this._http.post(`https://maps.googleapis.com/maps/api/geocode/json?address=`+parameters.city+`+`+parameters.state+`+`+parameters.pincode+`&key=`+key
,{headers:headers})

 .map( res => console.log(res.json()));
  }



  submitFormX (form): Observable<any> {

          let headers = new Headers()
          var imageLink = window.localStorage.getItem('imagefileurl');
          var access = window.localStorage.getItem('access_token')
          var secret = window.localStorage.getItem('secret_key')
           headers.append('access_token',access)
           headers.append('secret_key',secret)
           headers.append('Content-Type','application/json');

          console.log('submit-form-headers',headers)
          return this._http.get(`http://54.169.134.133:80/complaint/create?subject=`+form.subject+`&description=`+form.description+`&image=`+imageLink+`&latitude=12.45&longitude=21.54&address=`+form.address+`&district=`+form.district+`&state=`+form.state+`&pincode=`+form.pincode,{headers:headers}) // ...using post request
                           .map(res=> res.json()) // ...and calling .json() on the response to return data
                           .catch((error:any) => Observable.throw(error.json().error || 'Server error')); //...errors if any
      }


    passwordChange(form):Observable<any>{
      let headers = new Headers()
      var access = window.localStorage.getItem('access_token')
      var secret = window.localStorage.getItem('secret_key')
       headers.append('access_token',access)
       headers.append('secret_key',secret)
       headers.append('Content-Type','application/json');
      console.log('old_password',form.oldpass);
      console.log('new_password',form.newpass);
      //console.log('the',form);
      var newP = form.newpass;
      console.log('submit-form-headers',headers)
      return this._http.get(`http://54.169.134.133:80/user/update_password?old_password=`+form.oldpass+`&new_password=`+newP,{headers:headers}) // ...using post request
                       .map(res=> res.json()) // ...and calling .json() on the response to return data
                       .catch((error:any) => Observable.throw(error.json().error || 'Server error')); //...errors if any
    }






}
