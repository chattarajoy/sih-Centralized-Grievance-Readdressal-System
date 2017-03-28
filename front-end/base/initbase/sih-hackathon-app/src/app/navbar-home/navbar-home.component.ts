import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'navbarhome-component',
  templateUrl: './navbar-home.component.html',
  styleUrls: ['./navbar-home.component.css'],

})
export class NavbarHomeComponent {


 constructor(
   private _router: Router
 ){}

}
