/**
 * The `SharedModule` is an Angular module that encapsulates commonly used components, directives, and Angular Material modules
 * which can be shared across multiple feature modules in the application. This helps to avoid redundancy and promotes reusability.
 *
 * @module SharedModule
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// Angular Material modules
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSnackBarModule } from '@angular/material/snack-bar';

// Third-party modules
import { EllipsisModule } from 'ngx-ellipsis'; // Ensure this is the correct package and properly installed

// Components
import { HeaderComponent } from './components/header/header.component';
import { ThemeCardComponent } from './components/theme-card/theme-card.component';

@NgModule({
  declarations: [
    HeaderComponent,
    ThemeCardComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    EllipsisModule,

    MatGridListModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    MatSidenavModule,
    MatSnackBarModule
  ],
  exports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    EllipsisModule,
    HeaderComponent,
    ThemeCardComponent,

    MatGridListModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    MatSidenavModule,
    MatSnackBarModule
  ]
})
export class SharedModule {}
