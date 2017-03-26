import { Component,OnInit } from '@angular/core';
import { AppService } from '../../services/app-services';
import { Status } from '../classes/complaints_class/complaints_status';
@Component({
  selector: 'usercomplaint-component',
  templateUrl: './usercomplaint.component.html',
  styleUrls: ['./usercomplaint.component.css'],
  providers:[AppService]
})
export class UserComplaintComponent implements OnInit {
  //title = 'app works!';

  status:Status[];

  constructor(
    private _service:AppService
  ){}

  ngOnInit(){
    this.getStatus();
  }

  getStatus(){
    this._service.getStatusX()
     .subscribe(res=>{
       this.status = res;
       console.log(res[1].state);
       console.log(this.status.length);


     })
  }
}
