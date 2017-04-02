import { Component , OnInit } from '@angular/core';
import { AppService } from '../../services/app-services';
import { StatusX } from './pendingStats';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
@Component({
  selector: 'pend-component',
  templateUrl: './pendcomplaints.component.html',
  styleUrls: ['./pendcomplaints.component.css'],
  providers : [AppService]
})
export class PendCompComponent implements OnInit  {

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
