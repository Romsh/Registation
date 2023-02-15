import { Component, Input, OnInit,Inject, ElementRef} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Ievent } from 'src/app/interfaces/ievent';
import { AgendaComponent } from '../../agenda/agenda.component';
import { AjoutModalComponent } from '../ajout-modal/ajout-modal.component';
import { EditModalComponent } from '../edit-modal/edit-modal.component';
import { PlusModalComponent } from '../plus-modal/plus-modal.component';
import {DragDropModule} from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-cellule',
  templateUrl: './cellule.component.html',
  styleUrls: ['./cellule.component.css'],
})
export class CelluleComponent implements OnInit {

  @Input() event: Array<Ievent>;
  eventInInterval : Array<Ievent>;
  @Input() jour: string;
  @Input() dates: Date;
  @Input() tete: boolean;
  @Input() headColor:String;
  @Input() ac: AgendaComponent;

  nbSpan: number;
  plusDisplayed: boolean = false;
  ajoutDisplayed: boolean = false;

  constructor(public dialog: MatDialog, 
    private elementRef: ElementRef) {
  }
 
  ngOnInit(): void {
    this.nbSpan=0;

    this.eventInInterval = new Array<Ievent>();
    if(this.event){
      for(let e of this.event){
        if(this.estDansIntervale(e.date_debut,e.date_fin,this.dates)){
          this.eventInInterval.push(e);
        }
      }
    }
  }

  ajoutModal(a:AgendaComponent){
    let j = this.jour.split("-");
    const dialogRef = this.dialog.open(AjoutModalComponent, {
      width: '30rem',
      data: {ac:a, date:this.dates, jour:j[1]},
    });
  }

  editModal(e:Ievent,a:AgendaComponent){
    const dialogRef = this.dialog.open(EditModalComponent, {
      width: '30rem',
      data: {event:e,ac:a, date:this.dates},
    });
  }

  estDansIntervale(d1:Date,d2:Date,t:Date):boolean{
    let date1 = new Date((d1.getMonth()+1)+"/"+d1.getDate()+"/"+d1.getFullYear());
    let date2 = new Date((d2.getMonth()+1)+"/"+d2.getDate()+"/"+d2.getFullYear());
    let test = new Date((t.getMonth()+1)+"/"+t.getDate()+"/"+t.getFullYear());
    return date1<=test&&test<=date2;
  }


  getNbSpan(){
    return this.nbSpan;
  }

  getShortType(s:string):string{
    switch(s){
      case 'Autre':
          return "A";
        break;
      case 'Révision':
        return "R";
        break;
      case 'Inspection':
        return "I";
        break;
      case 'Panne':
        return "P";
        break;
      default:
        return "rien";
        break;
    }
  }

  getEventColor(e:Ievent):string{
    switch(e.type){
      case 'Autre':
          return "autre";
        break;
      case 'Révision':
        return "revision";
        break;
      case 'Inspection':
        return "inspection";
        break;
      case 'Panne':
        return "panne";
        break;
      default:
        return "rien";
        break;
    }
  }

  incSpan(){
    this.nbSpan++;
  }

  isPlusDisplayed():boolean{
    return this.plusDisplayed;
  }

  onClick(event) {
    if (!this.elementRef.nativeElement.contains(event.target)) // or some similar check
      this.ajoutDisplayed = false;
   }

  plusModal():void{
    const dialogRef = this.dialog.open(PlusModalComponent, {
      width: '250px',
      data: {eventData: this.eventInInterval, dates:this.dates, ac:this.ac},
    });
  }
  
  resetSpan(){
    this.nbSpan=0;
    this.plusDisplayed = false;
  }

  setPlusDisplayed(){
    this.plusDisplayed = true;
  }

  testSpan():boolean{
    if(this.nbSpan){
      return this.nbSpan>=2;
    }else{
      return false;
    }
  }

  testFini(i:number):boolean{
    if(this.eventInInterval){
      return i==this.eventInInterval.length;
    }else{
      return false;
    }
  }

  testJourActuel(str:string):boolean{
    let d = new Date();
    let d2 = new Date(str.split('-')[3]+'-'+str.split('-')[2]+'-'+str.split('-')[1]);
    if(str){
      return (d.getFullYear()==d2.getFullYear())&&(d.getMonth()==d2.getMonth())&&(d.getDate()==d2.getDate());
    }else{
      return false;
    }
  }

  testJourPasse():boolean{
    let d = new Date();
    d = new Date(d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate())
    if(this.dates){
      return this.dates<d&&this.dates!=d;
    }else{
      return false;
    }
  }
}
