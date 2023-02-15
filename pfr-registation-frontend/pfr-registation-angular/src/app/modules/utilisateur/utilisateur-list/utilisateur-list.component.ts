import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { IUtilisateur } from 'src/app/interfaces/iutilisateur';
import { IUtilisateurSimple } from 'src/app/interfaces/iutilisateurSimple';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UtilisateurService } from 'src/app/services/utilisateur-service/utilisateur.service';

@Component({
  selector: 'app-utilisateur-list',
  templateUrl: './utilisateur-list.component.html',
  styleUrls: ['./utilisateur-list.component.css']
})
export class UtilisateurListComponent implements OnInit {
  utilisateur: IUtilisateur;
  utilisateurs: IUtilisateurSimple[];
  matricule: string;

  currentUser: User;
  isResponsable: boolean = false;

  constructor(private utilisateurService: UtilisateurService, private route: ActivatedRoute,
    private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.utilisateurService.findAll().subscribe(res => {
      this.utilisateurs = res;
      console.log(this.utilisateurs);
    });

    this.currentUser = this.authService.getCurrentUser();
    if(this.currentUser){
      if(this.currentUser.role = "ROLE_REPONSABLE"){
        this.isResponsable = true;
        console.log("est bien responsable true");
        
      }else{
        this.isResponsable = false;
        console.log("n'est pas responsable");
      }
    }
    

  }

  supprimerUtilisateur(matricule: string) {
    console.log(matricule);
    this.utilisateurService.supprimerUtilisateur(matricule).subscribe(res => this.ngOnInit()
    )
  };

}
