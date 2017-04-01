import { Component, OnInit } from '@angular/core';
import { newPass } from './passwordNew';
import { UserformService } from '../../../services/userform.service';
import { Router } from '@angular/router';
import {AppService} from '../../../services/app-services';
import { ValidationManager } from "ng2-validation-manager";
import { NotificationsService } from 'angular2-notifications';
@Component({
  selector: 'passettings',
  templateUrl: './tings.component.html',
  styleUrls: ['./tings.component.css'],
  providers: [UserformService,AppService]
})
export class PasSettingComponent implements OnInit{
  // model = new newPass('','');
 form;

  updPass = {
    oldpass : '',
    newpass : '',
    renewpass : ''
  }

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

ngOnInit(){
  this.form = new ValidationManager({
     'oldpass' : 'required',
     'newpass' : 'required',
     'renewpass' : 'required|equalTo:newpass'

  });

   this.form.setValue('name', 'Default');
}
  passSubmit(){
    console.log('Entering now')
      this._service.passwordChange(this.updPass).subscribe((res)=>{
        console.log(res);
        if(res.status === "success"){
          this._notify.success('Ok','done');
        }else if(res.status === "error"){
          this._notify.error('Error Occured',res.error_message);
        }

      })

  }
}
