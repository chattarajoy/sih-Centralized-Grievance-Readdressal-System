import { Component, OnInit } from '@angular/core';
import '../../../../node_modules/chart.js/src/chart.js';
import { AppService } from '../../services/app-services';
import { pieChartDataX } from './counter';
@Component({
  selector: 'admin-pie',
  templateUrl: './admin-pie.component.html',
  styleUrls: ['./admin-pie.component.css'],
  providers : [AppService]
})
export class AdminPieComponent implements OnInit {


  public pieChartLabels:string[] = ['New Complaints', 'Pending Complaints', 'Completed Complaints'];
   public pieChartData:number[];
   public pieChartType:string = 'pie';

  public store = [];
    // events
    public chartClicked(e:any):void {
      console.log(e);
    }

    public chartHovered(e:any):void {
      console.log(e);
    }


  constructor(
    private _service : AppService
  ){

  }

 ngOnInit(){
  this.pieChart();
 }

 pieChart(){

  this._service.getStats().subscribe( res =>{
  this.store.push(res.new_complaint);
  this.store.push(res.pending_complaint);
  this.store.push(res.completed_complaint);this.pieChartData = this.store;


   })
 }


}
