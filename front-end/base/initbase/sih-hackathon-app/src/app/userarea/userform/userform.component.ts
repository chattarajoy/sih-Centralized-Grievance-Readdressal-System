import { Component ,OnInit } from '@angular/core';
import { newForm } from './userform';
import { UserformService } from '../../services/userform.service';
import { Router } from '@angular/router';
import {AppService} from '../../services/app-services';
import { NotificationsService } from 'angular2-notifications';
import { locationX } from './location';
import { ValidationManager } from "ng2-validation-manager";

require('aws-sdk/dist/aws-sdk');

@Component({
  selector: 'userform-component',
  templateUrl: './userform.component.html',
  styleUrls: ['./userform.component.css'],
  providers : [UserformService,AppService]
})
export class UserFormComponent implements OnInit{
  // formInput = {
  //   subject : '',
  //   description : '',
  //   city : '',
  //   state : '',
  //   pincode : ''
  //
  // }
  form;
  cForm = {
    subject : '',
    description : '',
    image : '',
    address : '',
    district : '',
    state : '',
    pincode : ''

  }

//  public imageSub : string;

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

  //model = new newForm('','','','','','',null);

  ngOnInit(){
    this.form = new ValidationManager({
    'subject' : 'required',
    'description' : 'required',
    'address' : 'required',
    'district' : 'required',
    'state' : 'required',
    'pincode' :'required'
    });

     this.form.setValue('name', 'Default');
  }

  formSubmit(){


    console.log('Entering',this.store)
      this._service.submitFormX(this.cForm).subscribe((res)=>{
        if(res.status === 'success'){
        console.log('Submitted!', res)
        this._notify.alert('Submitting...','Validating for Errors');
        setTimeout((_router) => {
            this._router.navigate(['sub']);
        }, 6500);
      }
      else if (res.status === 'error'){
        this._notify.error('Errors','Please Check Your Form',{
          showProgressBar : false
        });
      }
      })

  }

  //file-upload

 store = [''];

  fileEvent(fileInput: any){
     //
     var file = fileInput.target.files[0];
     this._serviceApp.awsService(file)





  }





}
