import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { inject, TestBed } from '@angular/core/testing';

import { UtilisateurService } from './utilisateur.service';

describe('UtilisateurService', () => {
  let mockHttp: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UtilisateurService]
    });

    mockHttp = TestBed.inject(HttpTestingController);
  });

  it('should be created', inject([UtilisateurService], (service: UtilisateurService) => {
    expect(service).toBeTruthy();
  }));
});
