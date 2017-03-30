import { Component } from '@angular/core';
import { newPass } from './passwordNew';
import { UserformService } from '../../../services/userform.service';
import { Router } from '@angular/router';
import {AppService} from '../../../services/app-services';
import { NotificationsService } from 'angular2-notifications';
@Component({
  selector: 'passettings',
  templateUrl: './tings.component.html',
  styleUrls: ['./tings.component.css'],
  providers: [UserformService,AppService]
})
export class PasSettingComponent {
  model = new newPass('','');

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


  formSubmit(){
    console.log('Entering',this.model)
      this._service.passwordChange(this.model).subscribe((res)=>{
        if(res.status === "success"){
          this._notify.success('Ok','done');
        }else if(res.status === "error"){
          this._notify.error('Error Occured',res.error_message);
        }

      })

  }
}
