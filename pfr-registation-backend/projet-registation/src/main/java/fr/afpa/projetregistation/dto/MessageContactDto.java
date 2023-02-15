package fr.afpa.projetregistation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageContactDto {

	private String nom;
	private String prenom;
	private String email;
	private String message;

}