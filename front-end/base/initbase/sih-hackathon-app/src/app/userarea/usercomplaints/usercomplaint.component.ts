import { Component,OnInit } from '@angular/core';
import { AppService } from '../../services/app-services';
import { Status } from '../classes/complaints_class/complaints_status';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
@Component({
  selector: 'usercomplaint-component',
  templateUrl: './usercomplaint.component.html',
  styleUrls: ['./usercomplaint.component.css'],
  providers:[AppService]
})
export class UserComplaintComponent implements OnInit {
  //title = 'app works!';

  // status:Status[] ;
  private status:Status[] = [];

  constructor(
    private _service:AppService
  ){}

  ngOnInit(){
    this.getStatus().subscribe( status => {
      this.status = status;
    });
  }

  getStatus(){
  return this._service.getStatusX()
    //  .subscribe(res=>{
    //    this.status = res;
    //    console.log(res);
    //    console.log(this.status.length);
     //
     //
    //  })
  }
}
