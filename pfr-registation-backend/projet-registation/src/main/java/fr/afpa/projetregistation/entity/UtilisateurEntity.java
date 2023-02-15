package fr.afpa.projetregistation.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe permet de définir les attributs de l'utilisateur
 * Et de faire les jointures avec Adresse et Connexion
 * 
 * @author Mathieu
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilisateur")
public class UtilisateurEntity {

	@Id
	@Column(name = "matricule")
	private String matricule;
	private String nom;
	private String prenom;
	
	@Temporal(TemporalType.DATE)
	private Date dateDeNaissance;
	
	private double salaire;
	private String mail;
	private String tel;
	private boolean responsable; 
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="idConnexion")
	private ConnexionEntity connexion;

	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="idAdresse", referencedColumnName="idAdresse", nullable=false)
	private AdresseEntity adresse;
	
	
}
