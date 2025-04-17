import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailsComponent } from './components/details/details.component';
import { FormComponent } from './components/form/form.component';
import { FeedComponent } from './components/feed/feed.component';

const routes: Routes = [
  { title: 'Feed', path: '', component: FeedComponent },
  { title: 'Form', path: 'form', component: FormComponent },
  { title: 'Details', path: ':id', component: DetailsComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArticlesRoutingModule {}
