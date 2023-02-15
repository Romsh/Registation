import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { UpdateMaterielComponent } from './update-materiel.component';

describe('UpdateMaterielComponent', () => {
  let component: UpdateMaterielComponent;
  let fixture: ComponentFixture<UpdateMaterielComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [ RouterModule.forRoot([]), HttpClientTestingModule, FormsModule],
      declarations: [ UpdateMaterielComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateMaterielComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
