import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NgbDate, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { IEditData } from 'src/app/interfaces/iedit-data';
import { Ievent } from 'src/app/interfaces/ievent';
import { EvenementService } from 'src/app/services/evenement/evenement.service';

@Component({
  selector: 'app-edit-modal',
  templateUrl: './edit-modal.component.html',
  styleUrls: ['./edit-modal.component.css']
})
export class EditModalComponent implements OnInit {
  model: NgbDateStruct = undefined;
  model2: NgbDateStruct= undefined;
  event: Ievent = {};
  date1 : Date;
  date2: Date;
  dateD: FormControl;
  dateF: FormControl;

  constructor(
    public dialogRef: MatDialogRef<EditModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: IEditData,
    public eServ:EvenementService,
    private dateAdapter: DateAdapter<Date>,
   ) {
      this.dateAdapter.setLocale('fr-FR');
   }

  ngOnInit(): void {
    if(this.data){
      this.event = this.data.event;
    }
    this.dateD = new FormControl(this.date1);
    this.dateF = new FormControl(this.date2);

  }

  close(){
    this.dialogRef.close();
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

  formatDate(d:Date){
    return this.event.date_debut.getFullYear()+"-"+(this.event.date_debut.getMonth()+1)+"-"+this.event.date_debut.getDate();
  }

  submitEdit(){
    this.eServ.updateEvenement(this.event).subscribe(res=>{
      this.data.ac.updateCalendar("n",this.data.date);
      this.dialogRef.close();
    });
  }

  submitDelete(){
    this.eServ.deleteEvenement(this.event).subscribe(res=>{
      this.data.ac.updateCalendar("n",this.data.date);
      this.dialogRef.close();
      if(this.data.pc){
        this.data.pc.onNoClick();
      }
    });
  }
}
