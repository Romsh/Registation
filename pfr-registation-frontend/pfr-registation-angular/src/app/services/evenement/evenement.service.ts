import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Icalendrier } from 'src/app/interfaces/icalendrier';
import { Ievent } from 'src/app/interfaces/ievent';
import { DatePipe, formatDate } from '@angular/common';
import { map } from 'jquery';
import { UtilisateurService } from '../utilisateur-service/utilisateur.service';

@Injectable({
  providedIn: 'root'
})
export class EvenementService {
  calendrier: Icalendrier;
  ev:Ievent;
  private url: string = "http://localhost:8080/evenement";

  headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient,public datepipe: DatePipe,
    private uServ: UtilisateurService) { }

  public findByYearMonth(user:string,year: number, month: number, action:string): Observable<Icalendrier> {
    if(action.length<=1){
      return this.http.get<Icalendrier>(this.url+"/"+user+"/"+year+"/"+month+"/"+action);
    }
  }

  public createEvenement(event:Ievent):Observable<Ievent>{
    event.user.dateDeNaissance = new Date(event.user.dateDeNaissance);
    return this.http.post<Ievent>(this.url,
      {titre:event.titre,type:event.type, description:event.description,date_debut:event.date_debut, date_fin:event.date_fin, user: event.user});

  }

  public updateEvenement(event:Ievent):Observable<Ievent>{
    event.user.dateDeNaissance = new Date(event.user.dateDeNaissance);
    return this.http.patch<Ievent>(this.url,
      {id:event.id,titre:event.titre,type:event.type, description:event.description,date_debut:event.date_debut, date_fin:event.date_fin, user: event.user});
  }

  public deleteEvenement(event:Ievent){
    return this.http.delete(this.url+"/"+event.id);
  }

/**  public findById(id: number): Observable<Imateriel> {
    return this.http.get<Imateriel>(this.url + "/" + id);
  }

  public addMateriel(mat: Imateriel) {
    return this.http.post<Imateriel>(this.url, mat);
  } */
}
