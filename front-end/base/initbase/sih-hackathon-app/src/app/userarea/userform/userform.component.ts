import { Component } from '@angular/core';
import { newForm } from './userform';
import { UserformService } from '../../services/userform.service';
import { Router } from '@angular/router';
import {AppService} from '../../services/app-services';

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

  constructor(
    private _service:UserformService,
    private _router : Router,
    private _serviceApp : AppService
  ){}

  model = new newForm('','','','',1);

  formSubmit(){
    console.log('Entering',this.model)
      this._service.submitFormX(this.model).subscribe((res)=>{
          this._router.navigate(['sub']);
      })

  }


}
