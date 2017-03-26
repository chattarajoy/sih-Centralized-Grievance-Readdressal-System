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
    console.log('from logout function');
    window.localStorage.removeItem('access_token');
    console.log('access token removed');
    window.localStorage.removeItem('secret_key');
    console.log('secret_key removed');
    console.log('returning to login');
      this._router.navigate(['']);
  }
}
