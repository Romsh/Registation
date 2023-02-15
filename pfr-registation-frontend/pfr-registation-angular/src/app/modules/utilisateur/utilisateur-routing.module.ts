import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { UtilisateurContactComponent } from './utilisateur-contact/utilisateur-contact.component';
import { UtilisateurFormComponent } from './utilisateur-form/utilisateur-form.component';
import { UtilisateurListComponent } from './utilisateur-list/utilisateur-list.component';

const routes: Routes = [
  { path: 'utilisateur/accueil', component: AccueilComponent },
  { path: 'utilisateur/liste', component: UtilisateurListComponent },
  { path: 'utilisateur/add', component: UtilisateurFormComponent },
  { path: 'utilisateur/contact', component: UtilisateurContactComponent },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UtilisateurRoutingModule { }
