import { Component , OnInit} from '@angular/core';
import { FormGroup , FormControl , FormBuilder , Validators} from '@angular/forms';
import { User } from './user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'app works!';
  public myForm : FormGroup;
  public submitted : boolean;
  public events : any[] =[]

  constructor( private _formX: FormBuilder ){}

  ngOnInit(){

  }

  save(model: User, isValid: boolean) {
        this.submitted = true; 


        console.log(model, isValid);
    }
}
