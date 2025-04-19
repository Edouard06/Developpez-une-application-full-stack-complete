import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { AuthService } from './features/auth/services/auth.service';
import { UserSessionService } from './core/services/user-session.service';

/**
 * The root component of the application.
 * 
 * @remarks
 * This component serves as the main layout for the application, including a 
 * side navigation menu. It provides methods to toggle the side navigation menu.
 * 
 * @component
 */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title(title: any) {
    throw new Error('Method not implemented.');
  }
  @ViewChild(MatSidenav) sidenav!: MatSidenav;


  constructor(
    private authService: AuthService,
    private userSessionService: UserSessionService
  ){}

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token && !this.userSessionService.isLogged) {
      this.authService.me().subscribe(user => {
        if (user) {
          this.userSessionService.logIn(user);
        } else {
          this.userSessionService.logOut(); 
        }
      });
    }
  }

  onSidenavToggle() {
    this.sidenav.toggle();
  }
}