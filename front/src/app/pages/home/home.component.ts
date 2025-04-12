import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

/**
 * The `HomeComponent` serves as the landing page of the application.
 * It provides navigation options for users to either log in or register.
 *
 * @component
 * @implements OnInit
 * @selector app-home
 * @templateUrl ./home.component.html
 * @styleUrls ['./home.component.scss']
 */
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  /**
   * Constructor for `HomeComponent`.
   * 
   * @param router - Router service to navigate between different routes in the application.
   */
  constructor(private router: Router) {}

  /**
   * Lifecycle hook that is called after the component has initialized.
   * Currently, no specific logic is implemented in this hook.
   */
  ngOnInit(): void {}

  /**
   * Navigates the user to the login page.
   */
  goToLogin() {
    this.router.navigateByUrl('/login');
  }

  /**
   * Navigates the user to the registration page.
   */
  goToRegister() {
    this.router.navigateByUrl('/register');
  }
}