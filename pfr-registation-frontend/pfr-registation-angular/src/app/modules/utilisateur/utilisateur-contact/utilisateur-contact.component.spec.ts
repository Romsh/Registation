import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

import { UtilisateurContactComponent } from './utilisateur-contact.component';

describe('UtilisateurContactComponent', () => {
  let component: UtilisateurContactComponent;
  let fixture: ComponentFixture<UtilisateurContactComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterModule.forRoot([]),
      ],
      providers : [FormBuilder],
      declarations: [ UtilisateurContactComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UtilisateurContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
