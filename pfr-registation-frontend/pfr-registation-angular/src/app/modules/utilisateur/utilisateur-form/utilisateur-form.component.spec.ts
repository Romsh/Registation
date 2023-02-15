import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { UtilisateurFormComponent } from './utilisateur-form.component';

describe('UtilisateurFormComponent', () => {
  let component: UtilisateurFormComponent;
  let fixture: ComponentFixture<UtilisateurFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [
        HttpClientTestingModule,
        RouterModule.forRoot([]),
      ],
      providers : [FormBuilder],
      declarations: [ UtilisateurFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UtilisateurFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
