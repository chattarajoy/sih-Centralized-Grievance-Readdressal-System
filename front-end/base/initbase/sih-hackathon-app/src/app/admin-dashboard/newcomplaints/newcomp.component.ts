import { Component , OnInit } from '@angular/core';
import { AppService } from '../../services/app-services';
import { StatusX } from './newStats';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
@Component({
  selector: 'new-component',
  templateUrl: './newcomp.component.html',
  styleUrls: ['./newcomp.component.css'],
  providers : [AppService]
})
export class NewCompComponent implements OnInit  {

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
        this.rec = res;
     })
   }


}
