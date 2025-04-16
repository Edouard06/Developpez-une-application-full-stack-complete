import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailsComponent } from './components/details/details.component';
import { FormComponent } from './components/form/form.component';
import { FeedComponent } from './components/feed/feed.component';

const routes: Routes = [
  { title: 'Form', path: 'create', component: FormComponent },
  { title: 'Details', path: 'details/:id', component: DetailsComponent },
  { title: 'Feed', path: '', component: FeedComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArticlesRoutingModule { }