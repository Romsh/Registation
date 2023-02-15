package fr.afpa.projetregistation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.afpa.projetregistation.dto.MaterielDto;
import fr.afpa.projetregistation.dto.TypeMaterielDto;

@Service
public interface ITypeMaterielService {

	/**
	 * Ajoute un type de matériel
	 * 
	 * @param pType type de matériel
	 * @return TypeMaterielDto
	 */
	TypeMaterielDto addType(TypeMaterielDto pType);

	/**
	 * récupère la liste des différents type de matériel
	 * 
	 * 
	 * @param pPageEnCours
	 * @return Liste des types de matériel
	 */
	List<TypeMaterielDto> getAll(int pPageEnCours);

	/**
	 * Récupère un type de matériel par son id
	 * 
	 * @param pId id du type de matériel.
	 * @return TypeMaterielDto le type recherché
	 */
	TypeMaterielDto getTypeById(int pId);

	/**
	 * Récupère un type de matériel par son libellé.
	 * 
	 * @param pLibelle libellé du type de matériel recherché.
	 * @return TypeMaterielDto : type de matériel recherché.
	 */
	TypeMaterielDto getTypeByLibelle(String pLibelle);

	/**
	 * Récupère le type de matériel du matériel sélectionné
	 * 
	 * @param pMat matériel sélectionné
	 * @return TypeMatariel : type de matériel recherché.
	 */
	TypeMaterielDto getTypeByMateriel(MaterielDto pMat);

	/**
	 * Modifie le type de matériel d'un matériel sélectionné.
	 * 
	 * @param pRef    id du matériel à modifier.
	 * @param Libelle nouveau libellé du matériel à modifier
	 */
	void updateTypeByLibelleAndRef(String pRef, String Libelle);
}
