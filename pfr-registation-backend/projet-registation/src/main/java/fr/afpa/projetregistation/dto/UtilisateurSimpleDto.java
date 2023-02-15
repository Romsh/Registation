package fr.afpa.projetregistation.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurSimpleDto {

	private String matricule;
	private String nom;
	private String prenom;
	private Date dateDeNaissance;
	private double salaire;
	private String mail;
	private String tel;
	private boolean responsable;

}
