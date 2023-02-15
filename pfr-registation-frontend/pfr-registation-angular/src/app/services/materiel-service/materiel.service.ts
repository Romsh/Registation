import { HttpClient,HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Imateriel } from 'src/app/interfaces/imateriel';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MaterielService {

  pageEnCours: number;
  list_materiel: Array<Imateriel> = new Array<Imateriel>();

  private url: string = "http://localhost:8080/materiel";

  formatsDateTest: string[] = ['dd/MM/yyyy'];
  dateNow: Date = new Date();

  constructor(private http: HttpClient) {
  }

  public findAll(pageEnCours: number): Observable<Imateriel[]> {
    
    return this.http.get<Imateriel[]>(this.url+"/liste/"+ pageEnCours);
  }

  public findById(id: number): Observable<Imateriel> {
    return this.http.get<Imateriel>(this.url + "/" + id);
  }


  public findAllByType(type: string, pageEnCours: number): Observable<Imateriel[]> {
console.log(type, pageEnCours );
    return this.http.get<Imateriel[]>(this.url + "/listeType/" + type+"/" + pageEnCours );
  }

  public addMateriel(mat: Imateriel) {
    return this.http.post<Imateriel>(this.url, mat);
  }

  public deleteMateriel(id: number) {
   return this.http.delete<Imateriel>(this.url + "/" + id );
  }


  public updateMateriel(mat:Imateriel){
    return this.http.patch<Imateriel>(this.url  + "/update",  mat);
  }
}
