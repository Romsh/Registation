import { inject, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { MaterielService } from './materiel.service';

describe('MaterielService', () => {
  let mockHttp: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [MaterielService]
    });

    mockHttp = TestBed.inject(HttpTestingController);
  });

  it('should be created', inject([MaterielService], (service: MaterielService) => {
    expect(service).toBeTruthy();
  }));
});
