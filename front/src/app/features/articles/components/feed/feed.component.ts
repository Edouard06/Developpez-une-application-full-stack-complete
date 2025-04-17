import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { ArticleService } from '../../services/article.service';
import { Article } from '../../interfaces/article';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss']
})
export class FeedComponent {
  public articles$: Observable<Article[]> = this.articleService.all();
  public sortOrder: string = 'desc';

  constructor(
    private articleService: ArticleService,
    private router: Router
  ) {}

  public goToForm(): void {
    this.router.navigate(["/feed/form"]);
  }

  public goToDetails(id: number): void {
    this.router.navigate([`/feed/${id}`]);
  }

  public updateOrder(): void {
    this.sortOrder = this.sortOrder === 'desc' ? 'asc' : 'desc';
    this.articles$ = this.articleService.all(this.sortOrder);
  }
}
