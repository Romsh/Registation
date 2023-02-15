package fr.afpa.projetregistation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "materiel")
public class MaterielEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_materiel")
	/**
	 * La ref ou référence sert d'identifiant au MaterielDto. Unique, non
	 * modifiable, auto-implémenté.
	 * 
	 */
	private int idMat;

	/**
	 * 
	 * Référence du matériel. Unique, requise, non incrémentée, non modifiable
	 */
	@Column(name = "ref")
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

	@Column(name = "date_achat")
	@Temporal(TemporalType.DATE)
	/**
	 * Date à laquelle le matériel a été acheté.
	 */
	private Date dateAchat;

	@ManyToOne
	@JoinColumn(name="id_type_materiel")
	private TypeMaterielEntity typeMaterielEntity;

}
