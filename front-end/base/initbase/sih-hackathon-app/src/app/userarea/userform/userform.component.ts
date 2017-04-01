import { Component } from '@angular/core';
import { newForm } from './userform';
import { UserformService } from '../../services/userform.service';
import { Router } from '@angular/router';
import {AppService} from '../../services/app-services';
import { NotificationsService } from 'angular2-notifications';
import { locationX } from './location';

require('aws-sdk/dist/aws-sdk');

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

  public location = '';

 public loc : locationX[];
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

  model = new newForm('','','','','',1);

  formSubmit(){


    console.log('Entering',this.loc)
      this._service.submitFormX(this.model).subscribe((res)=>{
        console.log('Submitted!', res)
        this._notify.alert('Submitting...','Validating for Errors');
        setTimeout((_router) => {
            this._router.navigate(['sub']);
        }, 6500);

      })

  }

  //file-upload


  fileEvent(fileInput: any){

     var file = fileInput.target.files[0];
     this._serviceApp.awsService(file)
      // var AWSService = window.AWS;
      // var file = fileInput.target.files[0];
      // console.log(file);
      //   this._notify.alert('Submitting...','Uploading the Image');
      // AWSService.config.accessKeyId = 'AKIAI4XWYQDCVLFHOW5Q';
      // AWSService.config.secretAccessKey = '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';
      // var bucket = new AWSService.S3({params: {ACL :"public-read" ,Bucket: 'asarcgrs'}});
      // var params = {Key: file.name, Body: file};
      // console.log(bucket);
      //
      // bucket.upload(params, function (err, data) {
      //     console.log(err, data);
      //     // console.log('location',data.Location);
      //     // retrieve(data.Location);
      //     this.location = data.Location;
      //     this.loc = this.location;
      //     var stuff = this.loc;
      //
      // });

      // function retrieve(locX){
      //   console.log('locX',locX);
      //   // this._serviceApp.checkLoc(locX);
      //



  }





}
