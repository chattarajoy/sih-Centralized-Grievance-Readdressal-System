import { Component, OnInit } from '@angular/core';
import { UserformService } from '../../../services/userform.service';
import { Router } from '@angular/router';
import {AppService} from '../../../services/app-services';
import { ValidationManager } from "ng2-validation-manager";
import { NotificationsService } from 'angular2-notifications';
@Component({
  selector: 'settings-nav',
  templateUrl: './settingsnav.component.html',
  styleUrls: ['./settingsnav.component.css'],
  providers:[AppService,UserformService]
})
export class SettingsNavComponent implements OnInit{
  form;

   verOTP = {
     aadhar_number : '',
     contact : ''
   }

   statusR = {
     status : ''
   }

   otpStat = {
     status : ''
   }

   finalOTP ={
     _otp : ''
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
      'aadhar_number' : 'required|minLength:12|maxLength:12',
      'contact' : 'required|minLength:10|maxLength:10'

   });

    this.form.setValue('name', 'Default');
 }

 verSubmit(){

this._service.verifyAadhar(this.verOTP).subscribe((res)=>{
  console.log(res);
  this.statusR.status = res.status;

  if(res.status === "success"){
    this._router.navigate(['/settings']);

  }else if(res.status === "error"){
    this._router.navigate(['/settings']);
    //this._notify.error('Error Occured',res.error_message);
  }

})

 }

 sendOTP(){
   this._service.verifyOTP(this.finalOTP).subscribe((res)=>{
     console.log(res);
     this.otpStat.status = res.status;

     if(res.status === "success"){
       this._notify.success('OTP','Verified');
     }else if(res.status === "error"){
       this._notify.error('Error','Check the Inputs');
       //this._notify.error('Error Occured',res.error_message);
     }

   })
 }

}
