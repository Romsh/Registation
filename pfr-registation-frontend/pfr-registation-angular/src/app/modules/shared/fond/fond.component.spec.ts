import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FondComponent } from './fond.component';

describe('FondComponent', () => {
  let component: FondComponent;
  let fixture: ComponentFixture<FondComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FondComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FondComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
