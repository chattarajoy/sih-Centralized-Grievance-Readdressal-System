import { Component } from '@angular/core';
import { AuthManager } from '../services/authmanager.service';

@Component({
  selector: 'navbar-component',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  providers:[AuthManager]
})
export class NavbarComponent {
  //title = 'app works!';
}
