import { AgendaComponent } from '../modules/evenement/agenda/agenda.component';
import { Ievent } from './ievent';

export interface IPlusData {
    eventData: Array<Ievent>;
    dates:Date;
    ac:AgendaComponent;
}
