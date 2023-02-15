import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Imateriel } from 'src/app/interfaces/imateriel';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MaterielService } from 'src/app/services/materiel-service/materiel.service';


@Component({
  selector: 'app-ajouter-materiel',
  templateUrl: './ajouter-materiel.component.html',
  styleUrls: ['./ajouter-materiel.component.css']
})
export class AjouterMaterielComponent {
  materiel: Imateriel = {};
  list_materiel: Array<Imateriel> = [];
  date: Date = new Date;
  


  typeMatList: string[] = ['POMPE', 'CUVE', 'BOUTIQUE'];


  
  materielForm = this.fb.group({
    ref: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(20)]],
    marque: ['',[Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
    modele: ['',[Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
    prix: [null, [Validators.required, Validators.min(1), Validators.max(1000000)]],
    etat: [null, [Validators.required, Validators.min(1), Validators.max(3)]],
    typeMateriel: ['',[Validators.required]],
    dateAchat: [this.date, [Validators.required]],
    localisation: ['',[Validators.required, Validators.minLength(3)]]
  })

  constructor(private route: ActivatedRoute,
    private router: Router, private materielService: MaterielService,
    private fb: FormBuilder,) {
     }


  addMateriel() {
    this.materiel = <Imateriel> this.materielForm.value;
      this.materielService.addMateriel(this.materiel).subscribe(result => this.gotoMaterielList());
    
  }

  gotoMaterielList() {
    this.router.navigate(['/materiel/liste']);
  }
}