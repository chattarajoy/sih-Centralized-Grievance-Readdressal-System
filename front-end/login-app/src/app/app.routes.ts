import { RouterModule , Routes } from '@angular/router';
import { LoginComponent} from './login/login-component';
import { DashboardComponent } from './dashboard/dashboard.component';
export const routes: Routes = [
{ path : '/login' , component: LoginComponent },
{ path : '/*other' , redirectTo:'/login' },
{ path : '/dashboard', component:DashboardComponent}


]
