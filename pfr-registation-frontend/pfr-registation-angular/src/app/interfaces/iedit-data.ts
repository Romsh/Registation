import { AgendaComponent } from '../modules/evenement/agenda/agenda.component';
import { PlusModalComponent } from '../modules/evenement/cellule/plus-modal/plus-modal.component';
import { Ievent } from './ievent';

export interface IEditData {
    event: Ievent;
    ac?:AgendaComponent;
    date?:Date;
    pc?:PlusModalComponent;
}
