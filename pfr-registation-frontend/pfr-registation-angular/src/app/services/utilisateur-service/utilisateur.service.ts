import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IUtilisateur } from 'src/app/interfaces/iutilisateur';
import { IUtilisateurSimple } from 'src/app/interfaces/iutilisateurSimple';
import { IMessageContact } from 'src/app/interfaces/imessageContact';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {
  url: string = "http://localhost:8080/utilisateur";

  constructor(private http: HttpClient) {

  }

  findAll(): Observable<IUtilisateurSimple[]> {
    return this.http.get<IUtilisateurSimple[]>(this.url);
  }
  save(utilisateur: IUtilisateur) {
    return this.http.post<IUtilisateur>(this.url, utilisateur);
  }
  supprimerUtilisateur(matricule: string) {
    return this.http.delete<IUtilisateur>(this.url + "/" + matricule);
  }
  contactUs(messageContact: IMessageContact): Observable<IMessageContact> {
    return this.http.post<IMessageContact>(this.url + "/contact", messageContact);
  }

  findByMatricule(matricule: string): Observable<IUtilisateurSimple> {
    return this.http.post<IUtilisateurSimple>(this.url + "/mat", matricule);
  }

}

