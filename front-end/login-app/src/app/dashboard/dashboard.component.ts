import { Component , OnInit, Input } from '@angular/core';
import { LoginComponent } from '../login/login-component';
import { Router } from '@angular/router';
import { Status } from '../status';
import { AuthServiceService } from '../services/auth-service.service';

@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers : [AuthServiceService]
})
export class DashboardComponent implements OnInit {

 @Input()
 someString : string;
  status : Status[];


  constructor(private _router:Router,
    private _service : AuthServiceService
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

   ngOnInit(){



   }



  getStatus(){
    this._service.getStatusX()
     .subscribe(res=>{
       this.status = res;
       console.log(this.status);
       console.log(this.status.length);


     })
  }




}
