import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { UnauthGuard } from './core/guards/unauth.guard';
import { AuthGuard } from './core/guards/auth.guard';
import { ProfileComponent } from './pages/profile/profile.component';
import { ListComponent } from './pages/theme-list/theme-list.component';

/**
 * Defines the routing configuration for the application.
 * 
 * @remarks
 * This module configures the application's routes, including lazy-loaded
 * modules and guards to control access to different routes based on the 
 * user's authentication state.
 * 
 * @module
 */
const routes: Routes = [
  { 
    canActivate: [UnauthGuard],
    path: '', 
    component: HomeComponent 
  },
  {
    canActivate: [UnauthGuard],
    path: '',
    loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule)
  },
  {
    canActivate: [AuthGuard],
    path: 'themes',
    component: ListComponent,
  },
  {
    canActivate: [AuthGuard],
    path: 'feed',
    loadChildren: () => import('./features/articles/articles.modules').then(m => m.ArticlesModule)
  },
  {
    canActivate: [AuthGuard],
    path: 'profile',
    component: ProfileComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}