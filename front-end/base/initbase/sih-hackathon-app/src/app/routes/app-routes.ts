import { RouterModule , Routes } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { LogSigComponent } from '../login_signup/log-sig.component';
import { UserComponent } from '../userarea/userarea.component';
import { AdminComponent } from '../admin/adminarea.component';
import { AppComponent } from '../app.component';
import { HomeComponent } from '../homearea/home.component';
import { AfterSubComponent } from '../afterSubmission/afterSub.component';
import { UserSettingComponent } from '../userarea/settingsarea/usersettings.component';
import { MainPageComponent } from '../mainpage/mainpage.component';
import { PasSettingComponent } from '../userarea/settingsarea/passwordsettings/tings.component';
import { AdminLoginComponent } from '../login_signup/adminlogarea/admin-login.component';
import { SettingsNavComponent } from '../userarea/settingsarea/settingsnav/settingsnav.component';
import { PendCompComponent } from '../admin-dashboard/pendcomplaints/pendcomplaints.component';
import { NewCompComponent} from '../admin-dashboard/newcomplaints/newcomp.component';
import { CompCompComponent } from '../admin-dashboard/compcomplaints/compcomp.component';
import { PendByIdComponent } from '../admin-dashboard/pendcomplaints/pendingbyid/pendbyid.component';

//services
import { AuthManager } from '../services/authmanager.service';
import { AdminAuthManager } from '../services/auth_admin_man';

export const routes: Routes = [
  //  { path : 'about' , component: AboutComponent},
  //   { path : '' , component: SearchComponent},
  //   { path : 'artist/:id' , component:ArtistComponent },
  //   { path : 'albums/:id' , component:AlbumComponent }
  { path : '' , component : MainPageComponent },
  { path : 'home' , component : HomeComponent},
  { path : 'user' , component : UserComponent , canActivate: [AuthManager]},
  { path : 'admin-login' , component : AdminLoginComponent},
  { path : 'admin' , component : AdminComponent , canActivate: [AdminAuthManager]},
  { path : 'sub' , component : AfterSubComponent , canActivate: [AuthManager]},
  { path : 'settings' , component : UserSettingComponent , canActivate: [AuthManager]},
  { path : 'password-settings' , component : PasSettingComponent , canActivate:[AuthManager]},
  { path : 'pending' , component : PendCompComponent , canActivate:[AdminAuthManager]},
  { path : 'newcomp' , component : NewCompComponent , canActivate:[AdminAuthManager]},
  { path : 'compcomp' , component : CompCompComponent , canActivate:[AdminAuthManager]},
  { path : 'pendbyid/:id' , component : PendByIdComponent , canActivate:[AdminAuthManager]}
]
