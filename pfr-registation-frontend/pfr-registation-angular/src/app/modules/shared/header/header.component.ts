import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { IUtilisateurSimple } from 'src/app/interfaces/iutilisateurSimple';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UtilisateurService } from 'src/app/services/utilisateur-service/utilisateur.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  panelOpenState = false;
  // subjectConnexion: Subject<number>;

  connect: string;
  isConnect: boolean = false;
  currentUser: User;
  utilisateurSimple: IUtilisateurSimple = {}; 

  mySubscription: any;

  constructor(private authService: AuthService, private router: Router, private utilisateurService: UtilisateurService) {}

  ngOnInit(): void {
    this.connect = localStorage.getItem('isConnected');
        
    if (this.connect == "true") {
      this.isConnect = true;

      this.currentUser = this.authService.getCurrentUser();
      this.utilisateurService.findByMatricule(this.currentUser.matricule).subscribe(res => {
        this.utilisateurSimple = res;
      });
    } else {
      this.isConnect = false;
      this.utilisateurSimple = {};
    }

  }
  logout() {
    this.authService.logout();
    this.ngOnInit();
    
  }


 

}
