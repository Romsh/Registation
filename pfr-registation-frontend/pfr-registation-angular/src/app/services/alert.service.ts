import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Alert } from '../models/alert';


@Injectable({
  providedIn: 'root'
})
export class AlertService {

  alertSubject: Subject<Alert>;

  constructor() {
    this.alertSubject = new Subject<Alert>();
  }

  addSuccess(msg: string) { this.add(msg, 'success') }
  addDanger(msg: string) { this.add(msg, 'danger') }
  addPrimary(msg: string) { this.add(msg, 'primary') }
  private add(message: string, type: string) {
    this.alertSubject.next({ type, message,closed:false});
  }

}
