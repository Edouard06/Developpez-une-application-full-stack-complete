import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Observable, of, map } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SubscriptionService } from 'src/app/core/services/subscription.service';
import { Theme } from '../../../features/articles/interfaces/theme';
import { SubscriptionRequest } from 'src/app/core/interfaces/subscription-request';
import { UnsubscriptionRequest } from 'src/app/core/interfaces/unsubscription-request';

@Component({
  selector: 'theme-card',
  templateUrl: './theme-card.component.html',
  styleUrls: ['./theme-card.component.scss']
})
export class ThemeCardComponent {
  @Input() public isThemePage: boolean = false;
  @Input() public theme!: Theme;
  @Output() public refreshSubscriptions = new EventEmitter<void>();
  public subscriptionId: number | undefined;
  public isSubscribed$: Observable<boolean> = of(false);

  constructor(
    private matSnackBar: MatSnackBar,
    private subscriptionService: SubscriptionService,
  ) {}

  public subscribe(id: number) {
    const request: SubscriptionRequest = { theme_id: id };
    this.subscriptionService.subscribe(request).subscribe((response) => {
      this.matSnackBar.open(response.message, "Close", { duration: 3000 });
      this.checkIfUserIsSubscribed();
    });
  }

  public checkIfUserIsSubscribed() {
    this.isSubscribed$ = this.subscriptionService.all().pipe(
      map(subscriptions => {
        const subscription = subscriptions.find(sub => sub.theme_id === this.theme.id);
        if (subscription) {
          this.subscriptionId = subscription.id;
          return true;
        }
        return false;
      })
    );
  }

  public unubscribe() {
    if (this.subscriptionId === undefined) {
      console.error('Subscription ID is undefined');
      return;
    }
    const request: UnsubscriptionRequest = { id: this.subscriptionId };
    this.subscriptionService.unSubscribe(request).subscribe((response) => {
      this.matSnackBar.open(response.message, "Close", { duration: 3000 });
      this.refreshSubscriptions.emit();
    });
  }

  ngOnInit() {
    this.checkIfUserIsSubscribed();
  }
}