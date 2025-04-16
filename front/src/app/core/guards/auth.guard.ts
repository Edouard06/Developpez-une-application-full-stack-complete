import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserSessionService } from '../services/user-session.service';

// The AuthGuard is an Angular guard that restricts access to certain routes based on the user's authentication status.
// It checks if the user is logged in by using the UserSessionService and redirects to the home page if not.
// This guard is typically used to protect routes that require authentication, ensuring that only logged-in users can access them.
@Injectable({ providedIn: 'root' })
export class AuthGuard  {

  constructor(
    private router: Router,
    private userSessionService: UserSessionService,
  ) {}

  public canActivate(): boolean {
    if (!this.userSessionService.isLogged) {
      this.router.navigate(['']);
      return false;
    }
    return true;
  }
}