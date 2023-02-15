package fr.afpa.projetregistation.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.afpa.projetregistation.dto.EvenementDto;

/**
 * Interface pour l'accès aux fonctionnalités liés aux @see EvenementEntity
 * 
 * @author Samuel
 *
 */

@Service
public interface IEvenementService {

	/**
	 * Récupère la liste complète des évènements
	 * @return @see List de @see EvenementDto
	 */
	public List<EvenementDto> getAll();
	
	/**
	 * Récupère un évènement à partir de son identifiant
	 * @param id de l'évènement en bdd
	 * @return
	 */
	public EvenementDto getById(int id);
	
	/**
	 * Récupère la liste des évènements
	 * compris entre les 2 dates placés en paramètres
	 * 
	 * @param debut de type @see Date
	 * @param fin de type @see Date
	 * @return @see List de @see EvenementDto
	 */
	public List<EvenementDto> getByDate(Date debut, Date fin, String mat);
	
	/**
	 * Récupère la liste des évènements
	 * à partir de la date placé en paramètre
	 * @param debut de type @see Date
	 * @return @see List de @see EvenementDto
	 */
	public List<EvenementDto> getByDate(Date debut);
	
	/**
	 * Récupère la liste des évènements 
	 * qui partagent le même type que celui placé en paramètre
	 * @param type de type @see String
	 * @return @see List de @see EvenementDto
	 */
	public List<EvenementDto> getByType(String type);
	
	/**
	 * Ajoute en base de donnée l'évènement placé en paramètre 
	 * et retourne ce dernier tel qu'il est en base de donnée
	 * @param evenement de type @see EvenementDto
	 * @return evenement de type @see EvenementDto
	 */
	public EvenementDto create(EvenementDto evenement);
	
	
	/**
	 * Met à jour la base de donnée à partir de l'évènement placé en paramètre
	 * et retourne ce dernier tel qu'il est en base de donnée
	 * @param evenement de type @see EvenementDto
	 * @return evenement de type @see EvenementDto
	 */
	public EvenementDto update(EvenementDto evenement);

	/**
	 * Supprime l'évènement de la base de donnée à partir de
	 * l'évènement placé en paramètre 
	 * @param evenement de type @see EvenementDto
	 */
	public void delete(EvenementDto evenement);
	
	/**
	 * Supprimer la totalité des entrées de la table Evenement
	 */
	public void deleteAll();
	
}
 