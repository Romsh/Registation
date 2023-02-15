import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AgendaComponent } from './agenda/agenda.component';
import { CelluleComponent } from './cellule/cellule/cellule.component';

const routes: Routes = [
  {path:'agenda', component:AgendaComponent},
  {path:'cellule', component:CelluleComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EvenementRoutingModule { }
