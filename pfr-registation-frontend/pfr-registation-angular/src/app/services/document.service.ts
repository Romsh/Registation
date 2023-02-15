import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpParams, HttpRequest} from '@angular/common/http';
import {findAll} from '@angular/compiler-cli/ngcc/src/utils';
import {Observable} from 'rxjs';
import {IDocument} from '../interfaces/idocument';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {
  documentsListe: Array<IDocument> = new Array<IDocument>();
  private documentsUrl: string;
  formatsDateTest2: string[] = ['dd/MM/yyyy'];
  dateNow2: Date = new Date();

  constructor(private  https: HttpClient) {
    this.documentsUrl = 'http://localhost:8080/document';
  }
  public findAll(): Observable<IDocument[]>{
    return this.https.get<IDocument[]>(this.documentsUrl);
    console.log(this.documentsListe);
  }
  public findById(idDocument: number): Observable<IDocument>{
    return this.https.get<IDocument>(this.documentsUrl + '/' + idDocument);
  }
  public findByNom(nomDocument: string): Observable<IDocument> {
    return this.https.get<IDocument>(this.documentsUrl + '/' + nomDocument);
  }
  public addDocument(document: IDocument): Observable<IDocument> {
    return this.https.post<IDocument>(this.documentsUrl, document);
  }
  public deleteDocument(idDocument: number): Observable<IDocument> {
    return this.https.delete<IDocument>(this.documentsUrl + '/' + 'delete' + idDocument);
  }
  public save(document: IDocument): Observable<any>{
    return this.https.post<IDocument>(this.documentsUrl, document);
  }

  ajouterDocument(document2: IDocument) {

  }
}
