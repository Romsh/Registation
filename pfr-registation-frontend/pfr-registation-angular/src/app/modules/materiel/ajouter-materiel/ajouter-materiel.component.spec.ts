import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { AjouterMaterielComponent } from './ajouter-materiel.component';

describe('AjouterMaterielComponent', () => {
  let component: AjouterMaterielComponent;
  let fixture: ComponentFixture<AjouterMaterielComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [
        RouterModule.forRoot([]),
        HttpClientTestingModule
      ],
      providers : [FormBuilder],
      declarations: [ AjouterMaterielComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AjouterMaterielComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
