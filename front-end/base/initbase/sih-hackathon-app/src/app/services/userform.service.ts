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

}
