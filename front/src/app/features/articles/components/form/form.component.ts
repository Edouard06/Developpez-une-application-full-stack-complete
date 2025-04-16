import { Component, ViewEncapsulation } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatLegacySnackBar as MatSnackBar } from '@angular/material/legacy-snack-bar';
import { Observable } from 'rxjs';
import { Theme } from 'src/app/features/articles/interfaces/theme';
import { ArticleRequest } from '../../interfaces/article-request';
import { ArticleService } from '../../services/article.service';
import { Article } from '../../interfaces/article';
import { ThemeService } from '../../../../core/services/theme.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class FormComponent {
  public themes$: Observable<Theme[]> = this.themeService.all();

  public form = this.builder.group({
    theme_id: ['', [Validators.required]],
    title: ['', [Validators.required]],
    content: ['', [Validators.required]]
  });

  constructor(
    private builder: FormBuilder,
    private themeService: ThemeService,
    private articleService: ArticleService,
    private router: Router,
    private matSnackBar: MatSnackBar
  ) {}

  public submit(): void {
    if (this.form.valid) {
      const articleRequest = this.form.value as ArticleRequest;
      this.articleService.create(articleRequest).subscribe((article: Article) => {
        if (article) {
          this.router.navigate([`/feed`]);
          this.matSnackBar.open("Article créé", "Close", { duration: 3000 });
        }
      });
    }
  }
}