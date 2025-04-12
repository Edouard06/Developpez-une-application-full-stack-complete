import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { AuthSuccess } from '../../interfaces/auth-success';
import { LoginRequest } from '../../interfaces/login-request';
import { Router } from '@angular/router';
import { UserSessionService } from 'src/app/core/services/user-session.service';
import { User } from 'src/app/core/interfaces/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  public form = this.builder.group({
    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ],
    password: [
      '',
      [
        Validators.required,
      ]
    ]
  });

  constructor(
    private builder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private userSessionService: UserSessionService,
  ) {}

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;

    localStorage.removeItem('token');

    this.authService.login(loginRequest).subscribe(
      (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.authService.me().subscribe((user: User) => {
          this.userSessionService.logIn(user);
          this.router.navigate(['/feed']);
        });
      },
    );
  }
}
