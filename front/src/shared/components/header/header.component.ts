import { Component, EventEmitter, Output } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { UserSessionService } from 'src/app/core/services/user-session.service';
import { filter, Observable } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  @Output() sidenavToggle = new EventEmitter<void>();
  public displayBackArrow: boolean = false;
  public routesToHideBack: string[] = ["/", "/themes", "/feed", "/profile"];
  public isLogged$: Observable<boolean> = this.userSessionService.$isLogged();

  constructor(
    private userSessionService: UserSessionService,
    private router: Router
  ) {}

  public back(): void {
    window.history.back();
  }

  public toggleSidenav(): void {
    this.sidenavToggle.emit();
  }

  ngOnInit(): void {
    this.router.events.pipe(
      filter((event): event is NavigationEnd => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.displayBackArrow = !this.routesToHideBack.includes(event.urlAfterRedirects);
    });
  }
}