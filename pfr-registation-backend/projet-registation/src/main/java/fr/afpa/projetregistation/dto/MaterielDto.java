package fr.afpa.projetregistation.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe regroupe les informations recueillies par les contrôleurs et
 * manipulées dans les interfaces et classe service notamment
 * MaterielSericeImpl.
 * 
 * @author Aurélien
 * @version 1.0
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterielDto {

	/**
	 * Id unique du matériel . Unique, auto-implémentée, non modifiable,
	 * auto-incrémentée.
	 * 
	 */
	private int idMat;

	/**
	 * La ref ou référence est preopre à chaque matériel MaterielDto. Unique, non
	 * modifiable, auto-implémenté.
	 * 
	 */
	private String ref;

	/**
	 * La marque correspond au constructeur du matériel en cours.
	 */
	private String marque;

	/**
	 * Le modele permet de savoir quelle version du matériel est utilisée.
	 */
	private String modele;

	/**
	 * Le prix correspond au prix d'achat du matériel.
	 */
	private double prix;

	/**
	 * La localisation correspond à la situation précise du matériel dans la
	 * station-service et permet de faciliter la prise en charge lors de certains
	 * évènements.
	 */
	private String localisation;

	/**
	 * L'etat renseigne sur la capacité du matériel à réaliser sa tache. Le chiffre
	 * renseigne sur l'état actuel à savoir 1 = OK, 2 = alerte si le fonctionnement
	 * est possible mais altéré et 3= Hors service.
	 */
	private int etat;

	/**
	 * Date à laquelle le matériel a été acheté.
	 */
	private Date dateAchat;

	/**
	 * Nom du type de matériel auquel le matériel en cours correspond. Exemple :
	 * pompe, cuve, machine à café...
	 */
	private String typeMateriel;

	public MaterielDto(String pRef, String pMarque, String pModele, int pPrix, int pEtat, 
			Date pDateAchat, String pTypeMateriel, String pLocalisation ) {
		this.ref = pRef;
		this.marque = pMarque;
		this.modele = pModele;
		this.prix = pPrix;
		this.etat = pEtat;
		this.dateAchat = pDateAchat;
		this.typeMateriel = pTypeMateriel;
		this.localisation = pLocalisation;

	}

	public MaterielDto(String pMarque, String pModele, int pPrix, String pLocalisation, int pEtat, Date pDateAchat,
			String pTypeMateriel) {

		this.marque = pMarque;
		this.modele = pModele;
		this.prix = pPrix;
		this.localisation = pLocalisation;
		this.etat = pEtat;
		this.dateAchat = pDateAchat;
		this.typeMateriel = pTypeMateriel;

	}

}
