import { Component , OnInit } from '@angular/core';

@Component({
  selector: 'usersettings',
  templateUrl: './usersettings.component.html',
  styleUrls: ['./usersettings.component.css']
})
export class UserSettingComponent implements OnInit {
  title = 'app works!';

  unlock = {
    status : ''
  }

  ngOnInit(){
    this.unlock.status = window.localStorage.getItem('aadhar_status');
  }
}
