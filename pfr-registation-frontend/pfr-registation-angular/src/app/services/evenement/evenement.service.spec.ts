import { fakeAsync, inject, TestBed } from '@angular/core/testing';
import { Icalendrier } from 'src/app/interfaces/icalendrier';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EvenementService } from './evenement.service';
import { DatePipe } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { Ievent } from 'src/app/interfaces/ievent';

describe('EvenementService', () => {
  let mockHttp: HttpTestingController;
  let eServ: EvenementService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [EvenementService,DatePipe,MatDialog]
    }); 

    mockHttp = TestBed.inject(HttpTestingController);
    eServ = TestBed.inject(EvenementService);

  });

  afterEach(() => {
    mockHttp.verify();
  });

  it('should be created', inject([EvenementService], (service: EvenementService) => {
    expect(service).toBeTruthy();
  }));
  
  

  it('findByYearMonth', fakeAsync(() =>{
    let tmp : Icalendrier;
    eServ.findByYearMonth('RESP001',2020,10,'n')
      .subscribe(_res =>{
          tmp = _res;
      })

      const req = mockHttp.expectOne('http://localhost:8080/evenement/RESP001/2020/10/n');

      req.flush({"current":{"year":2020,"month":10},"calendrier":["Jeudi-01-10-2020","Vendredi-02-10-2020","Samedi-03-10-2020","Dimanche-04-10-2020","Lundi-05-10-2020","Mardi-06-10-2020","Mercredi-07-10-2020","Jeudi-08-10-2020","Vendredi-09-10-2020","Samedi-10-10-2020","Dimanche-11-10-2020","Lundi-12-10-2020","Mardi-13-10-2020","Mercredi-14-10-2020","Jeudi-15-10-2020","Vendredi-16-10-2020","Samedi-17-10-2020","Dimanche-18-10-2020","Lundi-19-10-2020","Mardi-20-10-2020","Mercredi-21-10-2020","Jeudi-22-10-2020","Vendredi-23-10-2020","Samedi-24-10-2020","Dimanche-25-10-2020","Lundi-26-10-2020","Mardi-27-10-2020","Mercredi-28-10-2020","Jeudi-29-10-2020","Vendredi-30-10-2020","Samedi-31-10-2020"],"titre":"Octobre","success":1,"event":[{"id":6,"titre":"Test 3","type":"Inspection","description":"Voici un test en bonne et dû forme.","date_debut":"Oct 6, 2020, 2:00:00 AM","date_fin":"Oct 6, 2020, 2:00:00 AM","duree":0,"user":{"matricule":"RESP001","nom":"nomRESP","prenom":"prenomRESP","dateDeNaissance":"janv. 15, 1989","salaire":2500.0,"mail":"responsable@gmail.com","tel":"07.07.07.07.07","responsable":true}}]})
      
      expect(tmp).toBeDefined();
      expect(tmp.calendrier.length).toBeGreaterThan(0);

      if(tmp.event.length>0){
        expect(tmp.event[0].user).not.toBeNull();
      }

      expect(tmp.current).not.toBeNull();
      expect(tmp.current.month).toEqual(10);
      expect(tmp.current.year).toEqual(2020);

  }));

   it('createEvenement', fakeAsync(()=>{
      let data:Ievent = {
        "id":0,
        "titre":"test 0",
        "type":"Autre", 
        "description":"test unitaire",
        "date_debut":new Date("2020-10-01"), 
        "date_fin":new Date("2020-10-01"), 
        "user": {
          "matricule":"RESP001",
          "dateDeNaissance":new Date()
        }
      }
      eServ.createEvenement(data).subscribe();
      const req = mockHttp.expectOne('http://localhost:8080/evenement');
      req.flush(data);

      expect(req.request.method).toEqual('POST');
   }))
});
