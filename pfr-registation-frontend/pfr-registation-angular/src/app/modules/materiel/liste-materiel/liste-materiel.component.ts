import { Component, OnInit } from '@angular/core';
import { Imateriel } from 'src/app/interfaces/imateriel';
import { MaterielService } from 'src/app/services/materiel-service/materiel.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DeleteModalComponent } from '../delete-modal/delete-modal.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { InfoModalComponent } from '../info-modal/info-modal.component';



@Component({
  selector: 'app-liste-materiel',
  templateUrl: './liste-materiel.component.html',
  styleUrls: ['./liste-materiel.component.css']
})
export class ListeMaterielComponent implements OnInit {
  

  materiel: Imateriel = {};
  list_materiel: Array<Imateriel> = [];
  monType: string;
  pageEnCours: number;
 liste:ListeMaterielComponent = this;





  constructor(private materielService: MaterielService, public dialog: MatDialog, private fb: FormBuilder, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.pageEnCours = 1;
    this.materielService.findAll(this.pageEnCours).subscribe(data => {
      this.list_materiel = data;
    })
  }


  getAllMateriel(pageEnCours: number) {
    this.monType = null;
    this.materielService.findAll(pageEnCours).subscribe(data => {
      this.list_materiel = data;
      if(this.list_materiel.length==0){
      this.pageEnCours--;
        this.materielService.findAll(pageEnCours-1).subscribe(data => {
          this.list_materiel = data;})  
      }
    })
  }

  getAllByTypeInit(unType: string, pageEnCours: number) {
    this.pageEnCours = 1;
    $('#btn-first').css('display', 'none');
    this.monType=unType;
    this.materielService.findAllByType(unType, pageEnCours).subscribe(data => {
      this.list_materiel = data;
    })
  }

  getAllByType(monType: string, pageEnCours: number) {
 
    this.materielService.findAllByType(monType, pageEnCours).subscribe(data => {
      this.list_materiel = data;
      if(this.list_materiel.length==0){
        this.pageEnCours--;
          this.materielService.findAllByType(monType,pageEnCours-1).subscribe(data => {
            this.list_materiel = data;})  
        }
      })
    }


  redirectToUpdate(idToUpdate: number) {
    console.log(idToUpdate);
    this.router.navigate(['/materiel/modifier/' + idToUpdate]);
  }

  deleteModal(idModal: number) {
    const dialogRef = this.dialog.open(DeleteModalComponent, {
      width: '40rem',
      height: '31rem',
      data: { idMaterielUtils: idModal }
    });
  }

  infoModal(idModal: number) {
    const dialogRef = this.dialog.open(InfoModalComponent, {
      width: '30rem',
      height: '40rem',
      data: { idMaterielUtils: idModal }
    });
  }

  getFirst() {
    this.pageEnCours = 1;
    if (this.monType == null) {
      this.getAllMateriel(this.pageEnCours);
    } else {
      this.getAllByType(this.monType, this.pageEnCours);
    }
    $('#btn-first').css('display', 'none');
  }

  getPrevious() {
    if (this.pageEnCours > 1) {
      this.pageEnCours--;
      if (this.monType == null) {
        this.getAllMateriel(this.pageEnCours);
      } else {
        this.getAllByType(this.monType, this.pageEnCours);
      }
    } else {
      this.getAllMateriel(this.pageEnCours);
    }

    if( this.pageEnCours==1 ){
      $('#btn-first').css('display', 'none');
    }


  }

  getNext() {
    this.pageEnCours++;
    if (this.monType == null) {
      this.getAllMateriel(this.pageEnCours);
    } else {
      this.getAllByType(this.monType, this.pageEnCours);
    }
    $('#btn-first').css('display', 'inline-flex');
  }

  getLast() {

  }
}