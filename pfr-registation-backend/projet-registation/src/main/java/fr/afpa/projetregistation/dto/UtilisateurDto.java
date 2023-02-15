package fr.afpa.projetregistation.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class UtilisateurDto {

	
	private String matricule;
	private String password;
	private String nom;
	private String prenom;
	private Date dateDeNaissance;
	private double salaire;
	private String mail;
	private String tel;
	private boolean responsable;

	private int numero;
	private String rue;
	private String complement;
	private String codePostal;
	private String ville;
	private String pays;
	
	public UtilisateurDto(String matricule, String password, String nom, String prenom, Date dateDeNaissance,
			double salaire, String mail, String tel, boolean responsable, int numero, String rue, String complement,
			String codePostal, String ville, String pays) {
		super();
		this.matricule = matricule;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.salaire = salaire;
		this.mail = mail;
		this.tel = tel;
		this.responsable = responsable;
		this.numero = numero;
		this.rue = rue;
		this.complement = complement;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}

	
	
}
