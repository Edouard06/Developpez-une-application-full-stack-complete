import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Article } from '../../interfaces/article';
import { User } from 'src/app/core/interfaces/user';
import { UserService } from '../../../../core/services/user.service';
import { Theme } from '../../interfaces/theme';
import { ThemeService } from '../../../../core/services/theme.service';

@Component({
  selector: 'article-card',
  templateUrl: './article-card.component.html',
  styleUrls: ['./article-card.component.scss']
})
export class ArticleCardComponent implements OnChanges {
  @Input() public article: Article | null = null;
  @Input() public isDetail: boolean = false;

  public user$!: Observable<User>;
  public theme$!: Observable<Theme>;

  constructor(
    private router: Router,
    private themeService: ThemeService,
    private userService: UserService
  ) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['article'] && this.article && this.article.authorId) {
      this.user$ = this.userService.getUserById(this.article.authorId.toString());
      if (this.isDetail) {
        this.theme$ = this.themeService.findById(this.article.themeId.toString());
      }
    }
  }

  goToDetail(id: number): void {
    this.router.navigate([`/details/${id.toString()}`]);
  }
}