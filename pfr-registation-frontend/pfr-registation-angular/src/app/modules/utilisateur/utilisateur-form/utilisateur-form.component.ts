import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';
import { IUtilisateur } from 'src/app/interfaces/iutilisateur';
import { UtilisateurService } from 'src/app/services/utilisateur-service/utilisateur.service';


@Component({
  selector: 'app-utilisateur-form',
  templateUrl: './utilisateur-form.component.html',
  styleUrls: ['./utilisateur-form.component.css']
})
export class UtilisateurFormComponent implements OnInit {
  utilisateur: IUtilisateur = {};
  utilisateurs: Array<IUtilisateur> = [];
  utilisateurForm: FormGroup;

  constructor(private utilisateurService: UtilisateurService,
    private fb: FormBuilder, private route: ActivatedRoute,
    private router: Router) {
    this.initForm();
  }

  initForm(): void {
    this.utilisateurForm = this.fb.group({
      matricule: ['', Validators.required],
      nom: ['', [Validators.required , Validators.minLength(3)]],
      prenom: ['', [Validators.required , Validators.minLength(3)]],
      dateDeNaissance: ['', Validators.required],
      salaire: ['', Validators.required],
      mail: ['', Validators.required],
      tel: ['', Validators.required],
      responsable: ['', Validators.required],

      password: ['', [Validators.required , Validators.minLength(6)]],

      numero: ['', Validators.required],
      rue: ['', Validators.required],
      complement: [''],
      codePostal: ['', Validators.required],
      ville: ['', Validators.required],
      pays: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.utilisateurService.findAll().subscribe(res => {
      this.utilisateurs = res;
    });
  }
  ajouterUtilisateur() {
    this.utilisateur = <IUtilisateur> this.utilisateurForm.value;
      this.utilisateurService.save(this.utilisateur).subscribe(res => this.gotoUtilisateurListe());
  }

  gotoUtilisateurListe() {
    this.router.navigate(['/utilisateur/liste']);
  }
}
