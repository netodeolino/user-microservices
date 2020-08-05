import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UtilService } from '../util/util.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private utilService: UtilService) { }

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (!req.url.includes('/login') && !req.url.includes('/save') && !req.url.includes('/email')) {
      req = req.clone({ headers: req.headers.set("Authorization", `Bearer ${this.utilService.getToken()}`) });
    }
    
    return next.handle(req);
  }
}
