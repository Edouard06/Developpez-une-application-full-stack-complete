import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { UnauthGuard } from './core/guards/unauth.guard';
import { AuthGuard } from './core/guards/auth.guard';
import { ProfileComponent } from './pages/profile/profile.component';
import { ListComponent } from './pages/theme-list/theme-list.component';
import { NotFoundComponent } from './pages/not-found/not-found.component'; // 👈 crée ce composant

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [UnauthGuard]
  },
  {
    path: '',
    canActivate: [UnauthGuard],
    loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: 'themes',
    component: ListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'feed',
    loadChildren: () => import('./features/articles/articles.modules').then(m => m.ArticlesModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'not-found',
    component: NotFoundComponent
  },
  {
    path: '**',
    redirectTo: 'not-found' 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
