package fr.afpa.projetregistation.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Il s'agit de la classe permettant d'avoir toutes les infos possible d'un évènement 
 * 
 * @author Samuel
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "evenement")
public class EvenementEntity {

	/**
	 * Identifiant unique d'un évènement
	 * de @see Integer
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	/**
	 * Titre de l'évènement
	 * de type {@link String}
	 */
	private String titre;
	
	/**
	 * Type d'évènement parmis les 4 possibles :
	 *  - Autre
	 *  - Panne
	 *  - Révision
	 *  - Inspection
	 *  de type @see String
	 */
	private String type;
	
	/**
	 * Description détaillé de l'évènement
	 * de type @see String
	 */
	private String description;
	
	/**
	 * Date de debut de l'évènement
	 * de type @see Date
	 */
	private Date date_debut;
	
	/**
	 * Date de fin de l'évènement
	 * de type @see Date
	 */
	private Date date_fin;
	
	/**
	 * Durée de l'évènement exprimé en seconde
	 * de type @see Integer
	 */
	private int duree;
	
//	@ManyToOne
//	@JoinColumn(name="ref")
//	private MaterielEntity materiel;
	
	@ManyToOne
	@JoinColumn(name="matricule")
	private UtilisateurEntity user;
}
