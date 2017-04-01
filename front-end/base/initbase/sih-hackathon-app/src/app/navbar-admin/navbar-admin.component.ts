import { Component } from '@angular/core';
import { AdminAuthManager } from '../services/auth_admin_man';
import { Router } from '@angular/router';

@Component({
  selector: 'navbar-admin',
  templateUrl: './navbar-admin.component.html',
  styleUrls: ['./navbar-admin.component.css'],
  providers:[AdminAuthManager]
})
export class NavbarAdminComponent {
  //title = 'app works!';

 constructor(
   private _router: Router
 ){}

  logout(){


    window.localStorage.removeItem('admin_access_token');
    window.localStorage.removeItem('admin_secret_key');
    window.localStorage.removeItem('a');
    window.localStorage.removeItem('b');
    this._router.navigate(['']);

  }
}
