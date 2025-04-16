/**
 * The `SharedModule` is an Angular module that encapsulates commonly used components, directives, and Angular Material modules
 * which can be shared across multiple feature modules in the application. This helps to avoid redundancy and promotes reusability.
 *
 * @module SharedModule
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './components/header/header.component';
import { RouterModule } from '@angular/router';
import { EllipsisModule } from 'ngx-ellipsis';
import { ThemeCardComponent } from './components/theme-card/theme-card.component';

const materialModules = [
  MatGridListModule,
  MatCardModule,
  MatButtonModule,
  MatFormFieldModule,
  MatInputModule,
  MatIconModule,
  MatSelectModule,
  MatSidenavModule,
  MatSnackBarModule
];

@NgModule({
  declarations: [
    HeaderComponent,
    ThemeCardComponent
  ],
  imports: [
    RouterModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    EllipsisModule,
    ...materialModules
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HeaderComponent,
    EllipsisModule,
    ThemeCardComponent,
    ...materialModules
  ],
})
export class SharedModule { }