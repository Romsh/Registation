import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListeMaterielComponent } from './liste-materiel/liste-materiel.component';

import { AjouterMaterielComponent } from './ajouter-materiel/ajouter-materiel.component';
import { UpdateMaterielComponent } from './update-materiel/update-materiel.component';





const routes: Routes = [

  {path:'materiel/liste', component:ListeMaterielComponent},
  {path:'materiel/ajouter', component:AjouterMaterielComponent},
  {path:'materiel/modifier/:param1', component:UpdateMaterielComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MaterielRoutingModule { }
