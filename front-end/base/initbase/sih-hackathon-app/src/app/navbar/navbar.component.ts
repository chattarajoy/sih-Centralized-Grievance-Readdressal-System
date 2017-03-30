import { Component } from '@angular/core';
import { AuthManager } from '../services/authmanager.service';
import { Router } from '@angular/router';

@Component({
  selector: 'navbar-component',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  providers:[AuthManager]
})
export class NavbarComponent {
  //title = 'app works!';

 constructor(
   private _router: Router
 ){}

  logout(){


    window.localStorage.removeItem('access_token');
    window.localStorage.removeItem('secret_key');
    window.localStorage.removeItem('intercepted_access_token');
    window.localStorage.removeItem('intercepted_secret_key');
    window.localStorage.removeItem('a');
    window.localStorage.removeItem('b');
    this._router.navigate(['']);

  }
}
