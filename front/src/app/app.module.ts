import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { CoreModule } from './core/core.module';
import { AuthModule } from './features/auth/auth.module';
import { SharedModule } from '../shared/shared.module';
import { ArticlesModule } from './features/articles/articles.modules';
import { MatButtonModule } from '@angular/material/button';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';

import { JwtInterceptor } from './core/interceptors/jwt.interceptor';
import { AuthInterceptor } from './core/interceptors/auth.interceptor';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    CoreModule,
    AuthModule,
    SharedModule,
    ArticlesModule,
    MatButtonModule,
  ],
  declarations: [
    AppComponent,
    HomeComponent,
  ],
  providers: [
    provideHttpClient(withInterceptorsFromDi()),
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}