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
    headers.append('Access-Control-Allow-Origin', 'http://127.0.0.1:4200');
headers.append('Access-Control-Allow-Headers', 'Content-Type,Accept');
headers.append('Access-Control-Allow-Methods', 'POST');
console.log(headers);
    return this._http.post(`https://maps.googleapis.com/maps/api/geocode/json?address=`+parameters.city+`+`+parameters.state+`+`+parameters.pincode+`&key=`+key
,{headers:headers})

 .map( res => console.log(res.json()));
  }


   submitFormX(form){

    var access = window.localStorage.getItem('access_token');
    var secret = window.localStorage.getItem('secret_key');

   console.log(form);
    var headers = new Headers();
    headers.append('Content-type','application/x-www-form=urlencoded');
    headers.append('access_token',access);
    headers.append('secret_key',secret);
    console.log(headers);

   return new Promise ((resolve) => {

  //subject, description, image, latitude, longitude, city, state, pincode
     console.log(headers);
    this._http.post(`http://54.169.134.133:80/complaint/create?subject=`+form.subject+`&description=`+form.description+`&image=abcd&latitude=12.45&longitude=21.54&city=`+form.city+`&state=`+form.state+`&pincode=`+form.pincode,{headers:headers})
    .map( res => res.json())
      .subscribe((res) =>{
        console.log('from formsubmisson',res);

        if(res){
          console.log('successCheck');


           resolve(res);

        }
      })


    })


  }


}
