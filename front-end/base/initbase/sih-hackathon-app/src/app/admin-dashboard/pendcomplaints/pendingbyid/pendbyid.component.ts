import { Component , OnInit } from '@angular/core';
import { AppService } from '../../../services/app-services';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
@Component({
  selector: 'pendbyid-component',
  templateUrl: './pendbyid.component.html',
  styleUrls: ['./pendbyid.component.css'],
  providers : [AppService]
})
export class PendByIdComponent implements OnInit  {



  constructor(
    private _service : AppService
  ){

  }
  ngOnInit(){

  }




}
