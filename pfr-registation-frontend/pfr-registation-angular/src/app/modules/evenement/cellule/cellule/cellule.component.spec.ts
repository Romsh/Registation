import { Overlay } from '@angular/cdk/overlay';
import { InjectionToken } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { CelluleComponent } from './cellule.component';

describe('CelluleComponent', () => {
  let component: CelluleComponent;
  let fixture: ComponentFixture<CelluleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports : [MatDialogModule],
      providers:  [
        Overlay, 
        MatDialog,
      ],
      declarations: [ CelluleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CelluleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
