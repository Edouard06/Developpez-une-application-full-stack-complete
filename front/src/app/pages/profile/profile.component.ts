import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { combineLatest, map, Observable, tap } from 'rxjs';
import { Subscription } from 'src/app/core/interfaces/subscription';
import { User } from 'src/app/core/interfaces/user';
import { UserRequest } from 'src/app/core/interfaces/user-request';
import { UserSessionService } from 'src/app/core/services/user-session.service';
import { UserService } from 'src/app/core/services/user.service';
import { Theme } from 'src/app/features/articles/interfaces/theme';
import { ThemeService } from 'src/app/core/services/theme.service';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { SubscriptionService } from 'src/app/core/services/subscription.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  public form = this.builder.group({
    username: ['', [Validators.required]],
    email: ['', [Validators.required]],
    password: [''], 
    id: [0, [Validators.required]],
  });

  public user$!: Observable<User>;
  public subscriptionsWithThemes$!: Observable<(Subscription & { theme?: Theme })[]>;

  constructor(
    private userSessionService: UserSessionService,
    private builder: FormBuilder,
    private subscriptionService: SubscriptionService,
    private themeService: ThemeService,
    private authService: AuthService,
    private userService: UserService,
    private matSnackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.user$ = this.authService.me().pipe(
      tap((user) => {
        console.log('👤 USER FROM API =', user);
        this.form.patchValue({
          username: user.username,
          email: user.email,
          id: user.id,
        });
      })
    );
    this.loadSubscriptionsWithThemes();
  }

  private loadSubscriptionsWithThemes() {
    const subscriptions$ = this.subscriptionService.all();
    const themes$ = this.themeService.all();

    this.subscriptionsWithThemes$ = combineLatest([subscriptions$, themes$]).pipe(
      map(([subscriptions, themes]) => {
        const uniqueSubsByThemeId = subscriptions.filter(
          (sub, index, self) => index === self.findIndex(s => s.themeId === sub.themeId)
        );

        return uniqueSubsByThemeId.map((subscription) => {
          const theme = themes.find((theme) => theme.id === subscription.themeId);
          return {
            ...subscription,
            theme,
          };
        });
      })
    );
  }

  public submit(): void {
    const request: UserRequest = {
      username: this.form.value.username as string,
      email: this.form.value.email as string,
      id: this.form.value.id as number,
      ...(this.form.value.password ? { password: this.form.value.password } : {})
    };

    this.userService.update(request).subscribe((response) => {
      this.matSnackBar.open(response.message, 'Close', { duration: 3000 });
    });
  }

  public handleRefresh() {
    this.loadSubscriptionsWithThemes();
  }

  public logout(): void {
    this.userSessionService.logOut();
  }
}
