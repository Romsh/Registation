export class Document { constructor(private nomDocument?: string, private categorieDocument?: string, private dateAjoutDocument?: Date, private dateDerniereModificationDocument?: Date, private descriptionDocument?: string, private commentairesDocument?: string, private matriculeUtilisateur?: string){}
get nom(){
    return this.nomDocument;
}
set nom(nomDocument: string){
    this.nomDocument = this.nomDocument;
}
get categorie(){
    return this.categorie;
}
set categorie(categorieDocument: string){
    this.categorieDocument = this.categorieDocument;
}
get dateAjout(){
    return this.dateAjoutDocument;
}
set dateAjout(dateAjoutDocument: Date){
    this.dateAjoutDocument = this.dateAjoutDocument;
}
get dateDerniereModification(){
    return this.dateDerniereModificationDocument;
}
set dateDerniereModification(dateDerniereModificationDocument: Date){
    this.dateDerniereModificationDocument = this.dateDerniereModificationDocument;
}
get description(){
    return this.descriptionDocument;
}
set description(descriptionDocument: string){
    this.descriptionDocument = this.descriptionDocument;
}
get commentaires(){
    return this.commentairesDocument;
}
set commentaires(commentairesDocument: string){
    this.commentairesDocument = this.commentairesDocument;
}
get matricule(){
    return this.matriculeUtilisateur;
}
set matricule(matriculeUtilisateur: string){
    this.matriculeUtilisateur = this.matriculeUtilisateur;
}
}
