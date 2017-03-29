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
  public status:Observable<Status[]>;

  constructor(
    private _service:AppService
  ){}

  ngOnInit(){
    // this.status = this.todoService.todos;
    this.status = this._service.status;

    this._service.getStatusX();
  }


}
