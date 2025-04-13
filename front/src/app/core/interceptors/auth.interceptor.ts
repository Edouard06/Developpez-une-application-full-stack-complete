import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserSessionService } from '../services/user-session.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(
    private userSessionService: UserSessionService,
    private router: Router,
    private matSnackBar: MatSnackBar,
  ) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401 || error.status === 403) {
          this.userSessionService.logOut();
          this.router.navigate(['/']);
          this.matSnackBar.open("Session expirée", "Close", { duration: 3000 });
        }
        return throwError(error);
      })
    );
  }
}