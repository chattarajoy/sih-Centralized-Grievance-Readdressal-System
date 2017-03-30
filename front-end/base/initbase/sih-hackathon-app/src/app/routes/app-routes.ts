import { RouterModule , Routes } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { LogSigComponent } from '../login_signup/log-sig.component';
import { UserComponent } from '../userarea/userarea.component';
import { AdminComponent } from '../admin/adminarea.component';
import { AppComponent } from '../app.component';
import { HomeComponent } from '../homearea/home.component';
import { AfterSubComponent } from '../afterSubmission/afterSub.component';
import { UserSettingComponent } from '../userarea/settingsarea/usersettings.component';
import { PasSettingComponent } from '../userarea/settingsarea/passwordsettings/tings.component';
import { SettingsNavComponent } from '../userarea/settingsarea/settingsnav/settingsnav.component';
//services
import { AuthManager } from '../services/authmanager.service';

export const routes: Routes = [
  //  { path : 'about' , component: AboutComponent},
  //   { path : '' , component: SearchComponent},
  //   { path : 'artist/:id' , component:ArtistComponent },
  //   { path : 'albums/:id' , component:AlbumComponent }

  { path : '' , component : HomeComponent},
  { path : 'user' , component : UserComponent , canActivate: [AuthManager]},
  { path : 'admin' , component : AdminComponent},
  { path : 'sub' , component : AfterSubComponent , canActivate: [AuthManager]},
  { path : 'settings' , component : UserSettingComponent , canActivate: [AuthManager]},
  { path : 'password-settings' , component : PasSettingComponent , canActivate:[AuthManager]}
]
