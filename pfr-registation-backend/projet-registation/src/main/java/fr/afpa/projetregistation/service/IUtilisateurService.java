package fr.afpa.projetregistation.service;

import java.util.List;

import fr.afpa.projetregistation.dto.MessageContactDto;
import fr.afpa.projetregistation.dto.UtilisateurDto;
import fr.afpa.projetregistation.dto.UtilisateurSimpleDto;

public interface IUtilisateurService {

	UtilisateurDto create(UtilisateurDto pUtilisateur);

	void updateUtilisateur(UtilisateurDto pUtilisateur);

	UtilisateurDto getUtilisateurByMatricule(String pMatricule);
	
	UtilisateurSimpleDto getUtilisateurSimpleByMatricule(String pMatricule);

	UtilisateurDto getUtilisateurByName(String pNom);

	/**
	 * Retourne la liste de tous les utilisateurs
	 * 
	 * @return List de UtilisateurSimpleDto
	 */
	List<UtilisateurSimpleDto> getAllUtilisateurs(int pPageEnCours);

	/**
	 * Permet de retourner une liste de tous les emlpoyés (boolean responsable =
	 * false)
	 * 
	 * @return List de UtilisateurEntity qui sont des employés
	 */
	List<UtilisateurDto> getAllEmployes(int pPageEnCours);

	/**
	 * Permet de retourner une liste de tous les responsables (boolean responsable =
	 * true)
	 * 
	 * @return List de UtilisateurEntity qui sont des responsables
	 */
	List<UtilisateurDto> getAllResponsables(int pPageEnCours);

	public void deleteUtilisateurByMatricule(String pMatricule);

	public boolean authentification(String login, String motdepasse);

	void contactUs(MessageContactDto pMessage);


}
