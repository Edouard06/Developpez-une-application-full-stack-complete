import { NgModule } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { ArticlesRoutingModule } from './articles-routing.module';
import { DetailsComponent } from './components/details/details.component';
import { FormComponent } from './components/form/form.component';
import { ArticleCardComponent } from './components/article-card/article-card.component';
import { CommentComponent } from './components/comment/comment.component';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';

@NgModule({
  imports: [
    SharedModule,
    ArticlesRoutingModule
  ],
  declarations: [
    DetailsComponent,
    FormComponent,
    CommentComponent,
    ArticleCardComponent
  ],
  providers: [
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'outline' } }
  ]
})
export class ArticlesModule { }