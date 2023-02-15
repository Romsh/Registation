import { inject, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UploadFileServiceService } from './upload-file-service.service';

describe('UploadFileServiceService', () => {
  let mockHttp: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UploadFileServiceService]
    });

    mockHttp = TestBed.inject(HttpTestingController);
  });

  it('should be created', inject([UploadFileServiceService], (service: UploadFileServiceService) => {
    expect(service).toBeTruthy();
  }));
});
