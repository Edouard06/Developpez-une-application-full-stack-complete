import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Theme } from '../../features/articles/interfaces/theme';
import { SubscriptionService } from 'src/app/core/services/subscription.service';
import { ThemeService } from 'src/app/core/services/theme.service';

/**
 * The `ListComponent` is responsible for displaying a list of themes.
 * It interacts with the `ThemeService` to fetch the list of themes and makes it available
 * as an observable stream that can be used in the template.
 *
 * @component
 * @selector theme-list
 * @templateUrl ./theme-list.component.html
 * @styleUrl ./theme-list.component.scss
 */
@Component({
  selector: 'theme-list',
  templateUrl: './theme-list.component.html',
  styleUrl: './theme-list.component.scss'
})
export class ListComponent {

  public themes$: Observable<Theme[]> = this.themeService.all();

  
  constructor(
    private themeService: ThemeService,
    private subscriptionService: SubscriptionService
  ) {}
}