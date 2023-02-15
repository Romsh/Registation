import { Inject } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Router } from '@angular/router';
import { IMaterielUtils } from 'src/app/interfaces/i-materiel-utils';
import { Imateriel } from 'src/app/interfaces/imateriel';
import { MaterielService } from 'src/app/services/materiel-service/materiel.service';
import { ListeMaterielComponent } from '../liste-materiel/liste-materiel.component';

@Component({
  selector: 'app-delete-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.css']
})
export class DeleteModalComponent  {

  pageEnCours: number;
  list_materiel: Array<Imateriel> = [];
  liste  : ListeMaterielComponent;
  materiel: Imateriel = {};
  
  constructor(private fb: FormBuilder, private materielService: MaterielService,
    private router: Router,public dialogRef: MatDialogRef<DeleteModalComponent>,  @Inject(MAT_DIALOG_DATA) public data: IMaterielUtils) { }

  ngOnInit(): void {
    
  }


  deleteMateriel() {
    this.materielService.deleteMateriel(this.data.idMaterielUtils).subscribe(result => this.gotoMaterielList());
  }

  gotoMaterielList() {
    this.pageEnCours = 1;
    this.materielService.findAll(this.pageEnCours).subscribe(data => {
      this.list_materiel = data;
  });
    window.location.reload();
    this.dialogRef.close();
}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
