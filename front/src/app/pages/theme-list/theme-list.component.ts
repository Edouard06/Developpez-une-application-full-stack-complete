import { Observable } from 'rxjs';
import { Component } from '@angular/core';
import { ThemeService } from 'src/app/core/services/theme.service';
import { SubscriptionService } from 'src/app/core/services/subscription.service';
import { Theme } from '../../features/articles/interfaces/theme';

@Component({
  selector: 'theme-list',
  templateUrl: './theme-list.component.html',
  styleUrls: ['./theme-list.component.scss']
})
export class ListComponent {
  public themes$: Observable<Theme[]>;

  constructor(
    private subscriptionService: SubscriptionService,
    private themeService: ThemeService
  ) {
    this.themes$ = this.themeService.all();
  }
}