import { Component } from '@angular/core';
import { LoginComponent } from '../login/login-component';
import { Router } from '@angular/router';

@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  title = 'app works!';

  constructor(private _router:Router){}

  logout(){
    window.localStorage.removeItem('auth_key');
      this._router.navigate(['/login']);
  }
}
