import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { tap, catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { AlertService } from '../services/alert.service';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService,
    private alertService: AlertService) { }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    return next.handle(req).pipe(
      tap(evt => {
      }),
      catchError((err: any) => {
        if (err instanceof HttpErrorResponse
          && (err.status == 403 || err.status == 401)) {
          if (err.status == 401) {
            this.alertService.addDanger('mauvais username/password');
          }
          this.authService.logout();
        }
        return of(err);
      }));

  }

}
