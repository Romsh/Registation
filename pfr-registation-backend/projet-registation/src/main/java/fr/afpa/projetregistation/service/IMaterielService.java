package fr.afpa.projetregistation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.afpa.projetregistation.dto.MaterielDto;

@Service
public interface IMaterielService {

	/**
	 * Méthode permettant de créer un MaterielDto à partir des informations
	 * récupérées et le modifier en MaterielEntity
	 * 
	 * @param MaterielDto
	 * @return MaterielDto
	 */
	MaterielDto create(MaterielDto materiel);

	/**
	 * Méthode permettant de sélectionné un matériel par son Id.
	 * 
	 * @param pId : Id du matériel à rechercher
	 * @return MaterielDto : matériel recherché.
	 */
	MaterielDto getMaterielById(int pId);

	/**
	 * Méthode permettant de récupérer un MaterielDto par son Id à savoir la
	 * référence.
	 * 
	 * @param pRef : La référence du matériel
	 * @return MaterielDto
	 */
	MaterielDto getMaterielByRef(String pRef);

	/**
	 * Cette méthode permet de récupérer une liste de MatérielDto en appelant le
	 * MaterielDao
	 * 
	 * @return liste de MaterielDto
	 */
	List<MaterielDto> getAll(int pPageEnCours);

	/**
	 * Permet de récupérer une liste de matériel étant d'un type de matériel
	 * particulier.
	 * 
	 * @param pPageEncours : page actuelle.
	 * @param pType        : String correspondant libelle du type de matériel
	 *                     recherché.
	 * @return List<MaterielDto> une liste de matériel sélectionnés par libelle de
	 *         type matériel.
	 */
	List<MaterielDto> getAllByType(int pPageEnCours, String pType);

	/**
	 * Méthode permettant de réaliser la mise à jour des informations d'un
	 * MaterielDto.
	 * 
	 * @param pRef        la référence du Matériel à modifier.
	 * @param MaterielDto le MaterielDto possédant les nouvelles données.
	 * @return MaterielDto
	 */
	void updateByRef(String pRef, MaterielDto pMatDto);

	/**
	 * Méthode permettant de modifier l'état d'un matériel. (ex: une pompe qui
	 * fonctionnait qui devient HS)
	 * 
	 * @param pRef  Ref du matériel à modifier.
	 * @param pEtat int correspondant au nouvel état du matériel. ( 1 : ok; 2 :
	 *              alerte; 3 : HS)
	 * @return MaterielDto modifié
	 */
	void updateEtatByRef(String pRef, int pEtat);

	/**
	 * Permet de supprimer un matériel en sélectionnant sa référence
	 * 
	 * @param pRef
	 */
	void deleteByRef(String pRef);

	
	/**
	 * Permet de supprimer un matériel en sélectionnant son id
	 * 
	 * @param pId
	 */
	void deleteById(int pId);
	
	/**
	 * LOiste de matériel à supprimer par type
	 * 
	 * @param pPage    page en cours
	 * @param pLibelle type de matériel à supprimer
	 */
	void deleteAllByType(int pPage, String pType);

	/**
	 * Vérifie la présence d'un matériel par sa référence
	 * 
	 * @param pRef référence du matériel dont on vérifie la présence.
	 * @return booléen vrai ou faux
	 */
	boolean existsByRef(String pRef);

	long count();
	
	
	

}
