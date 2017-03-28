import { Component } from '@angular/core';
import { newForm } from './userform';
import { UserformService } from '../../services/userform.service';
import { Router } from '@angular/router';

@Component({
  selector: 'userform-component',
  templateUrl: './userform.component.html',
  styleUrls: ['./userform.component.css'],
  providers : [UserformService]
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
    private _router : Router
  ){}

  model = new newForm('','','','',1);

  formSubmit(){
    console.log('Entering',this.model)
      this._service.submitFormX(this.model).then((res)=>{
          console.log('the form',res)
      })

  }

}
