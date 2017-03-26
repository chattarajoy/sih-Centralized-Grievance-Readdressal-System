import { RouterModule , Routes } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { LogSigComponent } from '../login_signup/log-sig.component';
import { UserComponent } from '../userarea/userarea.component';
import { AdminComponent } from '../admin/adminarea.component';
import { AppComponent } from '../app.component';
import { HomeComponent } from '../homearea/home.component';

export const routes: Routes = [
  //  { path : 'about' , component: AboutComponent},
  //   { path : '' , component: SearchComponent},
  //   { path : 'artist/:id' , component:ArtistComponent },
  //   { path : 'albums/:id' , component:AlbumComponent }

  { path : '' , component : HomeComponent},
  { path : 'user' , component : UserComponent},
  { path : 'admin' , component : AdminComponent}

]
