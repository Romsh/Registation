import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Document2RoutingModule } from './document2-routing.module';
import { DocumentListeComponent } from './document-liste/document-liste.component';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import { DocumentFormComponent } from './document-form/document-form.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MAT_MOMENT_DATE_FORMATS, MatMomentDateModule, MomentDateAdapter} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatNativeDateModule} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';



@NgModule({
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'fr-FR'},
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
  ],
  declarations: [DocumentListeComponent, DocumentFormComponent],
  exports: [
    DocumentListeComponent, DocumentFormComponent
  ],
  imports: [
    CommonModule,
    Document2RoutingModule,
    MatCardModule,
    MatIconModule,
    MatFormFieldModule,
    MatDatepickerModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatNativeDateModule,
    MatMomentDateModule,
    MatInputModule
  ]
})
export class Document2Module { }
