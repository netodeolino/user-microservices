import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UtilService } from '../util/util.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

  private jwtHelperService: JwtHelperService;

  constructor(private router: Router, private utilService: UtilService) {
    this.jwtHelperService = new JwtHelperService();
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if (this.jwtHelperService.isTokenExpired(this.utilService.getToken())) {
        this.router.navigate(['login']);
        return false;
      }
    
      return true;
  }
  
}
