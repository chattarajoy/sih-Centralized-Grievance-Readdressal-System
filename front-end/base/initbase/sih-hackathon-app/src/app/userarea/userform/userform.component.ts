import { Component } from '@angular/core';
import { newForm } from './userform';
import { UserformService } from '../../services/userform.service';

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
    private _service:UserformService
  ){}

  model = new newForm('','','','',1);

  formSubmit(){
    console.log('Entering',this.model)
      this._service.getLocation(this.model)
      .subscribe(res=>{
        console.log(res);


      })
  }

}
