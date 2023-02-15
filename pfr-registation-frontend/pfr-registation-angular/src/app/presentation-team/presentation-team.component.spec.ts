import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PresentationTeamComponent } from './presentation-team.component';

describe('PresentationTeamComponent', () => {
  let component: PresentationTeamComponent;
  let fixture: ComponentFixture<PresentationTeamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PresentationTeamComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PresentationTeamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
