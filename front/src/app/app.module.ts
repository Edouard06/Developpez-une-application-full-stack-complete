import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { AuthModule } from './features/auth/auth.module';
import { SharedModule } from '../shared/shared.module';
import { CoreModule } from './core/core.module';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent, 
    HomeComponent, 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CoreModule,
    AuthModule,
    SharedModule,
    RouterModule
  ],
  
  bootstrap: [AppComponent],
})
export class AppModule {}