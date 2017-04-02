import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';
import {Jsonp} from '@angular/http';

@Injectable()
export class UserformService {

  constructor(
    private _http : Http,
    private _jsonP : Jsonp
  ) { }

  getLocation(parameters):Observable<any>{

    console.log(parameters);
    var headers = new Headers();
    var address = parameters.address;
    var newAddress = address.replace(/([,])/g,"+");
    console.log('modified address',newAddress);
    var key = 'AIzaSyBJSQWoYevjqqNZQmTrkNCUnZkDW656cbs';
    headers.append('Access-Control-Allow-Origin', '*');
    headers.append('Access-Control-Allow-Credentials', 'true');
    headers.append('Access-Control-Allow-Headers', 'Content-Type,Accept');
    headers.append('Access-Control-Allow-Methods', 'POST');
    headers.append('Authorization', null);
console.log(headers);
    return this._jsonP.get(`https://maps.googleapis.com/maps/api/geocode/json?address=`+newAddress+`+`+parameters.district+`+`+parameters.state+`+`+parameters.pincode+`&key=`+key+`&format=jsonP&prefix=JSONP_CALLBACK`
,{headers:headers})

 .map( res => console.log('is',res.json()));
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



    verifyAadhar(form):Observable<any>{
      let headers = new Headers()
      var access = window.localStorage.getItem('access_token')
      var secret = window.localStorage.getItem('secret_key')
       headers.append('access_token',access)
       headers.append('secret_key',secret)
       headers.append('Content-Type','application/json');
      console.log('submit-form-headers',headers)
      return this._http.get(`http://54.169.134.133:80/aadhar_verification/verify_aadhar_data?aadhar_number=`+form.aadhar_number+`&contact=`+form.contact,{headers:headers}) // ...using post request
                       .map(res=> res.json()) // ...and calling .json() on the response to return data
                       .catch((error:any) => Observable.throw(error.json().error || 'Server error')); //...errors if any
    }



   verifyOTP(form):Observable<any>{
     let headers = new Headers()
     var access = window.localStorage.getItem('access_token')
     var secret = window.localStorage.getItem('secret_key')
      headers.append('access_token',access)
      headers.append('secret_key',secret)
      headers.append('Content-Type','application/json');
     console.log('submit-form-headers',headers)
     return this._http.get(`http://54.169.134.133:80/aadhar_verification/verify_otp?otp=`+form._otp,{headers:headers}) // ...using post request
                      .map(res=> res.json()) // ...and calling .json() on the response to return data
                      .catch((error:any) => Observable.throw(error.json().error || 'Server error')); //...errors if any
   }



}
