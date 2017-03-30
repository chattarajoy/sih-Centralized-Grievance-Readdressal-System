import { Component } from '@angular/core';
import { newForm } from './userform';
import { UserformService } from '../../services/userform.service';
import { Router } from '@angular/router';
import {AppService} from '../../services/app-services';
import { NotificationsService } from 'angular2-notifications';

@Component({
  selector: 'userform-component',
  templateUrl: './userform.component.html',
  styleUrls: ['./userform.component.css'],
  providers : [UserformService,AppService]
})
export class UserFormComponent {
  // formInput = {
  //   subject : '',
  //   description : '',
  //   city : '',
  //   state : '',
  //   pincode : ''
  //
  // }

  public options = {
    position: ["bottom", "right"],
    timeOut: 5000,
    lastOnBottom: true,
    
}

  constructor(
    private _service:UserformService,
    private _router : Router,
    private _serviceApp : AppService,
    private _notify : NotificationsService
  ){}

  model = new newForm('','','','',1);

  formSubmit(){
    console.log('Entering',this.model)
      this._service.submitFormX(this.model).subscribe((res)=>{
        console.log('Submitted!')
        this._notify.alert('Submitting...','Validating for Errors');
        setTimeout((_router) => {
            this._router.navigate(['sub']);
        }, 6500);

      })

  }


}
