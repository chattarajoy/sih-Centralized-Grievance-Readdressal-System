import { Component } from '@angular/core';
import 'node_modules/chart.js/src/chart.js';

@Component({
  selector: 'admin-pie',
  templateUrl: './admin-pie.component.html',
  styleUrls: ['./admin-pie.component.css']
})
export class AdminPieComponent {

   public pieChartLabels:string[] = ['Download Sales', 'In-Store Sales', 'Mail Sales'];
    public pieChartData:number[] = [300, 500, 100];
    public pieChartType:string = 'pie';

    // events
    public chartClicked(e:any):void {
      console.log(e);
    }

    public chartHovered(e:any):void {
      console.log(e);
    }
}
