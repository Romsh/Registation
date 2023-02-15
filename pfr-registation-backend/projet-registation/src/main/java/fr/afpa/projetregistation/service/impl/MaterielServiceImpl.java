package fr.afpa.projetregistation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.afpa.projetregistation.controller.MaterielController;
import fr.afpa.projetregistation.dao.IMaterielDao;
import fr.afpa.projetregistation.dao.ITypeMaterielDao;
import fr.afpa.projetregistation.dto.MaterielDto;
import fr.afpa.projetregistation.entity.MaterielEntity;
import fr.afpa.projetregistation.entity.TypeMaterielEntity;
import fr.afpa.projetregistation.service.IMaterielService;
import fr.afpa.projetregistation.service.ITypeMaterielService;
import fr.afpa.projetregistation.utils.Constantes;
import lombok.extern.slf4j.Slf4j;

/**
 * Cette classe implémente l'interface IMaterielService afin de manipuler les
 * MaterielDto et MaterielEntity en vue d'appeler le MaterielDao
 * 
 * 
 * @author Aurélien
 * @version 1.0
 *
 */
@Slf4j
@Service
public class MaterielServiceImpl implements IMaterielService {

	/**
	 * Instanciation de l'interface matériel Dao.
	 */
	@Autowired
	IMaterielDao materielDao;

	/**
	 * Instanciation de l'interface typeMatériel Dao.
	 */
	@Autowired
	ITypeMaterielDao typeMaterielDao;

	ITypeMaterielService typeService;

	/**
	 * Instanciation de l'outil modelMapper permettant de simplifier la conversion
	 * d'un dto vers une entity et inversement.
	 */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Cette méthode permet d'ajouter un nouveau matérielDto et d'appeler le Dao
	 * afin d'enregistrer cet élément en base de donnée.
	 *
	 * @see MaterielController
	 * @param MaterielDto
	 * @return MaterielDto
	 */
	@Override
	public MaterielDto create(MaterielDto pMateriel) {
		boolean testExists = this.existsByRef(pMateriel.getRef());
		if (testExists == false) {
			MaterielEntity matEntity = this.modelMapper.map(pMateriel, MaterielEntity.class);

			String type = pMateriel.getTypeMateriel().toUpperCase();

			/**
			 * Vérifie la présence du type de matériel en BDD.
			 */
			Optional<TypeMaterielEntity> optionalType = typeMaterielDao.findByLibelleMateriel(type.toUpperCase());
			/**
			 * Enregistre le type si non présent en BDD
			 */
			if (!optionalType.isPresent()) {
				TypeMaterielEntity typeMaterielEntity = new TypeMaterielEntity(
						pMateriel.getTypeMateriel().toUpperCase());
				typeMaterielDao.save(typeMaterielEntity);
				Optional<TypeMaterielEntity> optionalType2 = typeMaterielDao.findByLibelleMateriel(type.toUpperCase());
				matEntity.setTypeMaterielEntity(optionalType2.get());

			}
			/**
			 * Ajoute le type déjà existant à la nouvelle entité.
			 */
			else {
				matEntity.setTypeMaterielEntity(optionalType.get());
			}
			matEntity = materielDao.save(matEntity);
			pMateriel = this.getMaterielByRef(matEntity.getRef());
			log.info("ajout avec succes");
		} else {
			pMateriel = this.getMaterielByRef(pMateriel.getRef());
			log.info("matériel déjà existant");
		}
		return pMateriel;
	}

	/**
	 * Récupère un matériel par son id.
	 * 
	 * @param pId id du matériel recherché
	 * @return MaterielDto Matériel recherché.
	 */
	@Override 
	public MaterielDto getMaterielById(int pId) {
		Optional<MaterielEntity> opsRes = materielDao.findById(pId);
		MaterielDto materiel = null;
		if (opsRes.isPresent()) {
			MaterielEntity matEntity = opsRes.get();
			materiel = this.modelMapper.map(matEntity, MaterielDto.class);
		}

		return materiel;
	} 

	/**
	 * Récupère un matériel par son ID. Mapping des informations.
	 * 
	 * @param pRef
	 * @return MaterielDto
	 */
	@Override
	public MaterielDto getMaterielByRef(String pRef) {
		Optional<MaterielEntity> opsRes = materielDao.findByRef(pRef);
		MaterielDto materiel = null;
		if (opsRes.isPresent()) {
			MaterielEntity matEntity = opsRes.get();
			materiel = this.modelMapper.map(matEntity, MaterielDto.class);
		}

		return materiel;
	}

	/**
	 * Cette méthode permet de récupérer une liste de MatérielDto, en appelant le
	 * MaterielDao. Le retour se fait sous forme de page d'un nombre défini par la constante
	 * ELEMENTS_PAR_PAGE et correspond à la page appelée par le controller.
	 * 
	 * @return List<MaterielDto> : liste de MaterielDto
	 * @param PageRequest  pPageEnCours: le numéro de la page désirée
	 */
	@Override
	public List<MaterielDto> getAll(int pPageEnCours) {
		List<MaterielDto> listeMateriel = new ArrayList<>();
		PageRequest page = PageRequest.of(pPageEnCours - 1, Constantes.ELEMENTS_PAR_PAGE);
		Page<MaterielEntity> listeMat = this.materielDao.findAll(page);
		for (MaterielEntity materielEntity : listeMat) {

			listeMateriel.add(this.modelMapper.map(materielEntity, MaterielDto.class));

		}
		return listeMateriel;
	}
	
	
	/**
	 * Cette méthode permet de récupérer une liste de MatérielDto, en appelant le
	 * MaterielDao, sélectionnés par type de matériel. Le retour se fait sous forme de page d'un nombre défini par la constante
	 * ELEMENTS_PAR_PAGE et correspond à la page appelée par le controller.
	 * 
	 * @return List<MaterielDto> : liste de MaterielDto
	 * @param PageRequest  pPageEnCours: le numéro de la page désirée
	 * @param String pType: num du type de matériel désiré
	 */
	@Override
	public List<MaterielDto> getAllByType(int pPageEnCours, String pType) {

		List<MaterielDto> listeMateriel = new ArrayList<>();
		
		PageRequest page = PageRequest.of(pPageEnCours - 1, Constantes.ELEMENTS_PAR_PAGE);
		Page<MaterielEntity> listeMat = this.materielDao.findAllByType( pType, page);
		for (MaterielEntity materielEntity : listeMat) {

			listeMateriel.add(this.modelMapper.map(materielEntity, MaterielDto.class));

		}
		return listeMateriel;
	}

	/**
	 * Cette méthode permet de modifier les informations d'un MaterielDto en se
	 * basant sur le MaterielDto ayant la même référence en base de donnée.
	 * 
	 * @return MaterielDto. Le MaterielDto mis à jour.
	 * @param int         pRef la référence du Materiel
	 * @param MaterielDto pMatDto. Le MaterielDto possédant les nouvelles
	 *                    informations.
	 */
	@Override
	public void updateByRef(String pRef, MaterielDto pMatDto) {
		Optional<MaterielEntity> optionelRes = materielDao.findByRef(pRef);
		MaterielEntity mat = null;
		if (optionelRes.isPresent()) {
			mat = optionelRes.get();
		}
		mat.setMarque(pMatDto.getMarque());
		mat.setModele(pMatDto.getModele());
		mat.setPrix(pMatDto.getPrix());
		mat.setLocalisation(pMatDto.getLocalisation());
		mat.setEtat(pMatDto.getEtat());
		materielDao.save(mat);
		log.info("Modification réalisée avec succès.");

	}

	@Override
	public void updateEtatByRef(String pRef, int pEtat) {

		Optional<MaterielEntity> optionelRes = materielDao.findByRef(pRef);
		MaterielEntity mat = null;
		if (optionelRes.isPresent()) {
			mat = optionelRes.get();
		}
		mat.setEtat(pEtat);
		materielDao.save(mat);

	}

	/**
	 * Permet de supprimer un matériel via sa référence
	 * 
	 * @param pRef Référence du materiel à supprimer
	 */
	@Override
	public void deleteByRef(String pRef) {

		Optional<MaterielEntity> opRes = materielDao.findByRef(pRef);
		MaterielEntity matEntity = null;
		if (opRes.isPresent()) {
			matEntity = opRes.get();
		}
		this.materielDao.delete(matEntity);

	}

	/**
	 * Permet de supprimer un matériel via son id
	 * 
	 * @param pRef id du materiel à supprimer
	 */
	@Override
	public void deleteById(int pId) {
		Optional<MaterielEntity> opRes = materielDao.findById(pId);
		MaterielEntity matEntity = null;
		if (opRes.isPresent()) {
			matEntity = opRes.get();
		}
		this.materielDao.delete(matEntity);
	}

	/**
	 * Méthodes permettant de supprimer tous les matériels d'un type choisi.
	 * 
	 * @param int      pPage page en cours
	 * @param pLibelle : String correspondant au libelle du type de materiel dont on
	 *                 veut supprimer toutes les instances.
	 */
	@Override
	public void deleteAllByType(int pPage, String pType) {

		List<MaterielDto> liste = this.getAllByType(pPage, pType);

		for (MaterielDto materielDto : liste) {
			this.deleteByRef(materielDto.getRef());
		}
	}

	@Override
	public boolean existsByRef(String pRef) {
		List<MaterielEntity> liste = materielDao.findAll();
		boolean res = false;
		for (MaterielEntity materiel : liste) {
			if (materiel.getRef().equals(pRef)) {
				res = true;
				break;
			}
		}

		return res;
	}
	
	@Override
	public long count() {
		return this.materielDao.count();
	}

}
