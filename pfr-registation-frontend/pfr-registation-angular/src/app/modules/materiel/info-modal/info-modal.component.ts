import { Inject } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Router } from '@angular/router';
import { IMaterielUtils } from 'src/app/interfaces/i-materiel-utils';
import { Imateriel } from 'src/app/interfaces/imateriel';
import { MaterielService } from 'src/app/services/materiel-service/materiel.service';
import { DeleteModalComponent } from '../delete-modal/delete-modal.component';

@Component({
  selector: 'app-info-modal',
  templateUrl: './info-modal.component.html',
  styleUrls: ['./info-modal.component.css']
})
export class InfoModalComponent implements OnInit {

  materiel: Imateriel = {};
  
  constructor(private fb: FormBuilder, private materielService: MaterielService,
    private router: Router, public dialog: MatDialog, public dialogRef: MatDialogRef<InfoModalComponent>,  @Inject(MAT_DIALOG_DATA) public data: IMaterielUtils) { }

  ngOnInit(): void {

   this.getMaterielById(this.data.idMaterielUtils);
   
  }

  
  getMaterielById(idMaterielUtils: number) {
    this.materielService.findById(idMaterielUtils).subscribe(res => {
      this.materiel = res;
    });
  }

  
  gotoMaterielList() {
    this.onNoClick(); 
    this.router.navigate(['/materiel']);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  
  redirectToUpdate(idToUpdate:number){
    this.dialogRef.close();
    this.router.navigate(['/materiel/modifier/'+ idToUpdate]);
  }

  deleteModal(idModal: number){
    const dialogRef = this.dialog.open(DeleteModalComponent, {
      width: '40rem',
      height:'31rem',
      data: {idMaterielUtils: idModal}     
    });
  }
}
