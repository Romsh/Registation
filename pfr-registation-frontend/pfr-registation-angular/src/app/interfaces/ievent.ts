import { IUtilisateurSimple } from './iutilisateurSimple';

export interface Ievent {
    id?:number;
    titre?:string;
    type?:string;
    description?:string;
    date_debut?:Date;
    date_fin?:Date;
    duree?:number;
    user?:IUtilisateurSimple;
}
