import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UtilisateurRoutingModule } from './utilisateur-routing.module';
import { AccueilComponent } from './accueil/accueil.component';
import { SharedModule } from '../shared/shared.module';
import { UtilisateurListComponent } from './utilisateur-list/utilisateur-list.component';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule, MatSelectionList} from '@angular/material/list';
import {MatBadgeModule} from '@angular/material/badge';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import { UtilisateurFormComponent } from './utilisateur-form/utilisateur-form.component';

import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';

import {MatInputModule} from '@angular/material/input';
import {MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { UtilisateurContactComponent } from './utilisateur-contact/utilisateur-contact.component';
import {MatTabsModule} from '@angular/material/tabs';
import { UtilisateurService } from 'src/app/services/utilisateur-service/utilisateur.service';



@NgModule({
  declarations: [AccueilComponent, UtilisateurListComponent, UtilisateurFormComponent, UtilisateurContactComponent],
  imports: [
    CommonModule,
    UtilisateurRoutingModule,
    SharedModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatSidenavModule,
    MatListModule,
    MatBadgeModule,
    MatCardModule,
    FlexLayoutModule,  
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatDialogModule,
    MatDialogModule, 
    MatInputModule, 
    MatCardModule, 
    MatFormFieldModule,
    MatSelectModule,
    MatTabsModule
    
  ],

  providers:[UtilisateurService]
})
export class UtilisateurModule { }
