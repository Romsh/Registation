import { Component, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {NgbDate, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';
import { Ievent } from 'src/app/interfaces/ievent';
import { EvenementService } from 'src/app/services/evenement/evenement.service';
import {AgendaComponent} from 'src/app/modules/evenement/agenda/agenda.component';
import { IAjoutData } from 'src/app/interfaces/i-ajout-data';
import { Inject } from '@angular/core';
import { DateAdapter } from '@angular/material/core';
import { UtilisateurService } from 'src/app/services/utilisateur-service/utilisateur.service';

@Component({
  selector: 'app-ajout-modal',
  templateUrl: './ajout-modal.component.html',
  styleUrls: ['./ajout-modal.component.css'],
})

export class AjoutModalComponent implements OnInit {
  model: NgbDateStruct = undefined;
  model2: NgbDateStruct= undefined;
  IsmodelShow:boolean;
  event: Ievent = {};
  ac : AgendaComponent;
  user:string;
  aj:Date;
  ispopUpShow: boolean = true;
  
  constructor(private fb: FormBuilder, private eserv: EvenementService,
    public dialogRef: MatDialogRef<AjoutModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: IAjoutData,
    private dateAdapter: DateAdapter<Date>,
    private uServ: UtilisateurService, 
    private elementRef: ElementRef
    ) { 
      this.dateAdapter.setLocale('fr-FR');
      dialogRef.disableClose = true;
    }

  ngOnInit(): void {
    this.event.date_debut = new Date(this.data.date);
    this.event.date_fin = new Date(this.data.date);
    if(localStorage.getItem("current_user")){
      this.user = JSON.parse(localStorage.getItem("current_user")).matricule;
      this.uServ.findByMatricule(this.user).subscribe(res=>{
        this.event.user = res;
      });
    }
  }


  ajouterEvenement(ac:AgendaComponent){
    if(this.event){
      this.eserv.createEvenement(this.event).subscribe(res=>{
        ac.updateCalendar("n",this.data.date);
        this.dialogRef.close();
      });
    } 
  }
  
  close(){
    this.event = {};
    this.dialogRef.close();
  }

  getFullMonth(m:number):string{
    if(m<10){
      return "0"+m;
    }else{
      return m+"";
    }
  }
}
