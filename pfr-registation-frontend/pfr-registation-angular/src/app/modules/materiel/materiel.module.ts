import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterielRoutingModule } from './materiel-routing.module';
import { SharedModule } from '../shared/shared.module';
import { ListeMaterielComponent } from './liste-materiel/liste-materiel.component';
import { AjouterMaterielComponent } from './ajouter-materiel/ajouter-materiel.component';
import { UpdateMaterielComponent } from './update-materiel/update-materiel.component';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule, MatSelectionList} from '@angular/material/list';
import {MatBadgeModule} from '@angular/material/badge';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';

import {MatInputModule} from '@angular/material/input';
import {MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import {MatTabsModule} from '@angular/material/tabs';
import {MaterielService} from '../../services/materiel-service/materiel.service';
import { DeleteModalComponent } from './delete-modal/delete-modal.component';
import { InfoModalComponent } from './info-modal/info-modal.component';


@NgModule({
  declarations: [ListeMaterielComponent,  AjouterMaterielComponent, UpdateMaterielComponent, DeleteModalComponent, InfoModalComponent],
  imports: [
    CommonModule,
    MaterielRoutingModule,
    SharedModule,
    MatToolbarModule,
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
    MatTabsModule,
    MatIconModule,
    MatButtonModule 
  ],
  providers: [MaterielService]
})
export class MaterielModule { }
