import { Component } from '@angular/core';
import { UserFormComponent } from './userform/userform.component';
import { UserComplaintComponent } from './usercomplaints/usercomplaint.component';
import { AuthManager } from '../services/authmanager.service';
@Component({
  selector: 'userarea-component',
  templateUrl: './userarea.component.html',
  styleUrls: ['./userarea.component.css'],
  providers: [AuthManager]
})
export class UserComponent {
  //title = 'app works!';
}
