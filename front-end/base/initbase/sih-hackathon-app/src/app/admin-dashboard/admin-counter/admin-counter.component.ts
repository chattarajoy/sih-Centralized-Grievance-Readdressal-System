import { Component , OnInit } from '@angular/core';
import './counter.js';
import { AppService } from '../../services/app-services';

@Component({
  selector: 'admin-counter',
  templateUrl: './admin-counter.component.html',
  styleUrls: ['./admin-counter.component.css'],
  providers : [AppService]
})
export class AdminCounterComponent implements OnInit {

   counter = {
     new :'',
     old: '',
     comp : ''
   }


  constructor(
    private _service : AppService
  ){

  }

   ngOnInit(){
    this.getStatus();
   }

   getStatus(){
   this._service.getStats().subscribe( res=>{
     this.counter.new = res.new_complaint;
     this.counter.old = res.pending_complaint;
     this.counter.comp = res.completed_complaint;

   })
   }

}
