package fr.afpa.projetregistation.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.projetregistation.entity.UtilisateurEntity;

@Repository
public interface IUtilisateurDao extends PagingAndSortingRepository<UtilisateurEntity, String> {

	/**
	 * Permet de récupérer un Utilisateur grâce à son nom
	 * 
	 * @param String le nom de l'utilisateur recherché déclaré en paramètre de la
	 *        méthode
	 * @return Optional UtilisateurEntity récupéré ayant pour nom le string placé en
	 *         pamaètre.
	 */
	Optional<UtilisateurEntity> findByNom(String pNom);

	/**
	 * Permet de retourner une liste de tous les emlpoyés (boolean responsable =
	 * false) Utilisation d'une requête native custom pour récupérer que les
	 * employés Utilisation de la pagination
	 * 
	 * @param page
	 * 
	 * @return List de UtilisateurEntity qui sont des employés
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM utilisateur WHERE responsable = 0")
	Page<UtilisateurEntity> findAllEmployes(PageRequest page);

	/**
	 * Permet de retourner une liste de tous les responsables (boolean responsable =
	 * true) Utilisation d'une requête native custom pour récupérer que les
	 * responsables Utilisation de la pagination
	 * 
	 * @return List de UtilisateurEntity qui sont des responsables
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM utilisateur WHERE responsable = 1")
	Page<UtilisateurEntity> findAllResponsables(PageRequest page);
}
