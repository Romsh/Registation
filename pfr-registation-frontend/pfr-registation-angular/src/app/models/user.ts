export class User {
    matricule: string;
    nom: string;
    prenom: string;
    dateDeNaissance: Date;
    mail: string;
    tel: string;
    role: string;
    password: string;
    
    constructor(matricule?: string, nom?: string, prenom?: string, tel?: string, dateDeNaissance?: Date, mail?: string, role?: string) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.mail = mail;
        this.tel = tel;
        this.role = role;

    }
}
