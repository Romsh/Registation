import { Icurrent } from './icurrent';
import { Ievent } from './ievent';

export interface Icalendrier {
    current?:Icurrent;
    calendrier?: Array<string>;
    titre?: string;
    event?:Array<Ievent>; 
}
