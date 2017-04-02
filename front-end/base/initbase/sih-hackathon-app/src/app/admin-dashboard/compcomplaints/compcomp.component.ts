import { Component , OnInit } from '@angular/core';
import { AppService } from '../../services/app-services';
import { StatusX } from './compStats';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
@Component({
  selector: 'comp-component',
  templateUrl: './compcomp.component.html',
  styleUrls: ['./compcomp.component.css'],
  providers : [AppService]
})
export class CompCompComponent implements OnInit  {

   rec = {
     id : '',
     office_id : '',
     status : '',
    category : '',
    date :''
   }

  constructor(
    private _service : AppService
  ){

  }
  ngOnInit(){
 this.getService();
  }

   getService(){
     this._service.adminFetchComp().subscribe( res =>{
        console.log('reach',res);
        this.rec = res;
        console.log(this.rec);
     })
   }


}
