package fr.afpa.projetregistation.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto permettant la transmission des évènements
 * 
 * @author Samuel
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvenementDto {
	
	/**
	 * Identifiant unique de  l'évènement
	 * de type @see Integer
	 */
	private int id;
	
	/**
	 * Titre de l'évènement
	 * de type {@link String}
	 */
	private String titre;
	
	/**
	 * Type de l'évènement parmis 4 choix :
	 * 	- Autre
	 *  - Panne
	 *  - Révision
	 *  - Inspection
	 *  de type @see String
	 */
	private String type;
	
	/**
	 * Description détailé de l'évènement
	 * de type @see String
	 */
	private String description;
	
	/**
	 * Date de début de l'évènement
	 * de type @see Date
	 */
	private Date date_debut;
	
	/**
	 * Date de fin de l'évènement
	 * de type @see Date
	 */
	private Date date_fin;
	
	/**
	 * Durée de l'évènement exprimé en
	 * seconde(s) de type @see Integer
	 */
	private int duree;
	
	/**
	 * L'utilisateur responsable de l'ajout de l'évènement
	 * en base de donnée
	 */
	private UtilisateurSimpleDto user;
	
	/**
	 * Le possible matériel en panne
	 */
//	private MaterielEntity materiel;
}
