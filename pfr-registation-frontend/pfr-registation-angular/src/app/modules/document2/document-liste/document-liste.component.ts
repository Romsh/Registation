import { Component, OnInit } from '@angular/core';
import {IDocument} from '../../../interfaces/idocument';
import {DocumentService} from '../../../services/document.service';

@Component({
  selector: 'app-document-liste',
  templateUrl: './document-liste.component.html',
  styleUrls: ['./document-liste.component.css']
})
export class DocumentListeComponent implements OnInit {

  document2: IDocument;
  documentList2: Array<IDocument> = [];


  constructor(private documentService: DocumentService) { }

  ngOnInit(): void {
    this.documentService.findAll().subscribe( data => {
      this.documentList2 = data;
      console.log(this.documentList2);
    });
  }

}
