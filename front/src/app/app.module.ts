import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { HTTP_INTERCEPTORS, HttpClientModule, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { AuthModule } from './features/auth/auth.module';
import { SharedModule } from './shared/shared.module';
import { CoreModule } from './core/core.module';
import { JwtInterceptor } from './core/interceptors/jwt.interceptor';
import { ArticlesModule } from '../app/features/articles/articles.modules';
import { ProfileComponent } from './pages/profile/profile.component';
import { ListComponent } from './pages/theme-list/theme-list.component';
import { AuthInterceptor } from './core/interceptors/auth.interceptor';

/**
 * The main module for the application.
 * 
 * @remarks
 * This module imports necessary Angular modules and application-specific modules,
 * declares the components used in the application, and provides HTTP interceptors
 * for handling authentication and authorization.
 * 
 * @module
 */
@NgModule({
 
  declarations: [
    AppComponent, 
    HomeComponent, 
    ProfileComponent, 
    ListComponent
  ],
 
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CoreModule,
    AuthModule,
    SharedModule,
    ArticlesModule,
  ],
  
 
  providers: [
    provideHttpClient(withInterceptorsFromDi()),
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}