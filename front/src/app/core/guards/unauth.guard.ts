import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserSessionService } from '../services/user-session.service';

// The UnauthGuard is an Angular guard that restricts access to certain routes for authenticated users.
// It checks if the user is logged in by using the UserSessionService and redirects to the feed page if they are.
// This guard is typically used to protect routes that should only be accessible to unauthenticated users, such as login and registration pages.


@Injectable({ providedIn: 'root' })
export class UnauthGuard  {


  constructor(
    private router: Router,
    private userSessionService: UserSessionService,
  ) {}

  public canActivate(): boolean {
    if (this.userSessionService.isLogged) {
      this.router.navigate(['feed']);
      return false;
    }
    return true;
  }
}