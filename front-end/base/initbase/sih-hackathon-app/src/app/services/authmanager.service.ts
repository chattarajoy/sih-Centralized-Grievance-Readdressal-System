import { Injectable } from '@angular/core';
import {
    CanActivate,
    Router,
    ActivatedRouteSnapshot,
    RouterStateSnapshot
} from '@angular/router';

@Injectable()
export class AuthManager implements CanActivate {

    constructor(private router: Router) {}

    canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if(next.url[0].path == 'login'){
            if(window.localStorage.getItem('access_token') && window.localStorage.getItem('secret_key')){
                return false;
            }
            else {
                return true;
            }
        }

        if(window.localStorage.getItem('access_token') && window.localStorage.getItem('secret_key')) {
            return true;
        }

        alert('Please log in !');
        this.router.navigate(['']);
        return false;
    }

}
