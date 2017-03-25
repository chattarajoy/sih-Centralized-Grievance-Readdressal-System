import { RouterModule , Routes } from '@angular/router';
import { LoginComponent} from './login/login-component';
import { DashboardComponent } from './dashboard/dashboard.component';
export const routes: Routes = [
{ path : '' , component: LoginComponent },
{ path : '*other' , redirectTo:'' },
{ path : 'dashboard', component:DashboardComponent}


]
