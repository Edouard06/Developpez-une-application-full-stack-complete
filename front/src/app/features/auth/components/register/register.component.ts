import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { AuthSuccess } from '../../interfaces/auth-success';
import { RegisterRequest } from '../../interfaces/register-request';
import { Router } from '@angular/router';
import { UserSessionService } from 'src/app/core/services/user-session.service';
import { User } from 'src/app/core/interfaces/user';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  public form = this.builder.group({
    username: [
      '',
      [
        Validators.required,
      ]
    ],
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
        Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/)
      ]
    ]
  });

 
  constructor(
    private builder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private userSessionService: UserSessionService
  ) {}

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe(
      (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.authService.me().subscribe((user: User) => {
          this.userSessionService.logIn(user);
          this.router.navigate(['/feed']);
        });
      }
    );
  }
}