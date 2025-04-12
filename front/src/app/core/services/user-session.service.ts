import { Injectable } from '@angular/core';
import { User } from '../interfaces/user';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserSessionService {
  public isLogged = false;
  public user: User | undefined;
  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  public logIn(user: User): void {
    this.user = user;
    this.isLogged = true;
    this.next();
  }

  public logOut(): void {
    localStorage.removeItem('token');
    this.user = undefined;
    this.isLogged = false;
    this.router.navigate(['/']);
    this.next();
  }

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }
}