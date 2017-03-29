import { Component , OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'afterSub-component',
  templateUrl: './afterSub.component.html',
  styleUrls: ['./afterSub.component.css']
})
export class AfterSubComponent implements OnInit{
  //title = 'app works!';

  constructor(private _router : Router){

  }

  ngOnInit(){
    setTimeout((_router) => {
        this._router.navigate(['user']);
    }, 5000);
  }
}
