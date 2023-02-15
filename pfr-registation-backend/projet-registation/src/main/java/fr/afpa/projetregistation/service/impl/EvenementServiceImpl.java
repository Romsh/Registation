package fr.afpa.projetregistation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.projetregistation.dao.IEvenementDao;
import fr.afpa.projetregistation.dao.IUtilisateurDao;
import fr.afpa.projetregistation.dto.EvenementDto;
import fr.afpa.projetregistation.dto.UtilisateurSimpleDto;
import fr.afpa.projetregistation.entity.EvenementEntity;
import fr.afpa.projetregistation.entity.UtilisateurEntity;
import fr.afpa.projetregistation.service.IEvenementService;
import lombok.extern.slf4j.Slf4j;

/**
 * Implémentation de l'interface @see IEvenementService
 * 
 * @author Samuel
 *
 */
@Slf4j
@Service
public class EvenementServiceImpl implements IEvenementService{

	@Autowired
	IEvenementDao edao;

	@Autowired
	IUtilisateurDao udao;
	
	/**
	 * Récupère la liste complète des évènements
	 * @return @see List de @see EvenementDto
	 */
	@Override
	public List<EvenementDto> getAll() {
		log.info("Obtention de la liste des évènements : complète");
		Iterable<EvenementEntity> itE = edao.findAll();
		Iterator<EvenementEntity> it = itE.iterator();
		List<EvenementDto> list = new ArrayList<>(); 
		while(it.hasNext()) {
			EvenementEntity ee = it.next();
			UtilisateurSimpleDto user = UtilisateurSimpleDto.builder().matricule(ee.getUser().getMatricule())
					.nom(ee.getUser().getNom()).prenom(ee.getUser().getPrenom()).dateDeNaissance(ee.getUser().getDateDeNaissance())
					.salaire(ee.getUser().getSalaire()).mail(ee.getUser().getMail()).tel(ee.getUser().getTel())
					.responsable(ee.getUser().isResponsable()).build();
			
			//A MODIFIER POUR INCLURE L'UTILISATEUR
			list.add(new EvenementDto(ee.getId(),
						ee.getTitre(),
						ee.getType(),
						ee.getDescription(),
						ee.getDate_debut(),
						ee.getDate_fin(),
						ee.getDuree(),
						user
					));
		}
		
		if(list.size()==0) list = null;
		
		return list;
	}

	/**
	 * Récupère un évènement en base de donnée 
	 * à partir d'un identifiant
	 * @param id de type @see Integer
	 * @return @see EvenementDto
	 */
	@Override
	public EvenementDto getById(int id) {
		log.info("Obtention de l'évènement d'id "+id);
		Optional<EvenementEntity> optEE = edao.findById(id);
		if(optEE.isPresent()) {
			EvenementEntity ee = optEE.get();
			UtilisateurSimpleDto user = UtilisateurSimpleDto.builder().matricule(ee.getUser().getMatricule())
					.nom(ee.getUser().getNom()).prenom(ee.getUser().getPrenom()).dateDeNaissance(ee.getUser().getDateDeNaissance())
					.salaire(ee.getUser().getSalaire()).mail(ee.getUser().getMail()).tel(ee.getUser().getTel())
					.responsable(ee.getUser().isResponsable()).build();
			
			EvenementDto edto = EvenementDto.builder()
									.id(ee.getId())
									.titre(ee.getTitre())
									.type(ee.getType())
									.description(ee.getDescription())
									.date_debut(ee.getDate_debut())
									.date_fin(ee.getDate_fin())
									.duree(ee.getDuree())
									.user(user).build();
			return edto;
		}else {
			return null;
		}
	}
	
	/**
	 * Récupère la liste des évènements
	 * compris entre les 2 dates placés en paramètres
	 * 
	 * @param debut de type @see Date
	 * @param fin de type @see Date
	 * @return @see List de @see EvenementDto
	 */
	@Override
	public List<EvenementDto> getByDate(Date debut, Date fin, String mat) {
		log.info("Obtention de la liste des évènements : par date de début ("+debut+") et fin("+fin+")");
		Iterable<EvenementEntity> itE = edao.findByDate(debut, fin, mat);
		Iterator<EvenementEntity> it = itE.iterator();
		List<EvenementDto> list = new ArrayList<>(); 
		while(it.hasNext()) {
			EvenementEntity ee = it.next();
			UtilisateurSimpleDto user = UtilisateurSimpleDto.builder().matricule(ee.getUser().getMatricule())
					.nom(ee.getUser().getNom()).prenom(ee.getUser().getPrenom()).dateDeNaissance(ee.getUser().getDateDeNaissance())
					.salaire(ee.getUser().getSalaire()).mail(ee.getUser().getMail()).tel(ee.getUser().getTel())
					.responsable(ee.getUser().isResponsable()).build();
			//A MODIFIER POUR INCLURE L'UTILISATEUR
			list.add(new EvenementDto(ee.getId(),
						ee.getTitre(),
						ee.getType(),
						ee.getDescription(),
						ee.getDate_debut(),
						ee.getDate_fin(),
						ee.getDuree(),
						user
					));
		}
		
		if(list.size()==0) list = null;
		
		return list;
	}

	/**
	 * Récupère la liste des évènements
	 * à partir de la date placé en paramètre
	 * @param debut de type @see Date
	 * @return @see List de @see EvenementDto
	 */
	@Override
	public List<EvenementDto> getByDate(Date debut) {
		log.info("Obtention de la liste des évènements : par date de début ("+debut+")");
		Iterable<EvenementEntity> itE = edao.findByDate(debut);
		Iterator<EvenementEntity> it = itE.iterator();
		List<EvenementDto> list = new ArrayList<>(); 
		while(it.hasNext()) {
			EvenementEntity ee = it.next();
			UtilisateurSimpleDto user = UtilisateurSimpleDto.builder().matricule(ee.getUser().getMatricule())
					.nom(ee.getUser().getNom()).prenom(ee.getUser().getPrenom()).dateDeNaissance(ee.getUser().getDateDeNaissance())
					.salaire(ee.getUser().getSalaire()).mail(ee.getUser().getMail()).tel(ee.getUser().getTel())
					.responsable(ee.getUser().isResponsable()).build();
			//A MODIFIER POUR INCLURE L'UTILISATEUR
			list.add(new EvenementDto(ee.getId(),
						ee.getTitre(),
						ee.getType(),
						ee.getDescription(),
						ee.getDate_debut(),
						ee.getDate_fin(),
						ee.getDuree(),
						user
					));
		}
		
		if(list.size()==0) list = null;
		
		return list;
	}

	/**
	 * Récupère la liste des évènements 
	 * qui partagent le même type que celui placé en paramètre
	 * @param type de type @see String
	 * @return @see List de @see EvenementDto
	 */
	@Override
	public List<EvenementDto> getByType(String type) {
		log.info("Obtention de la liste des évènements : par type d'évènement ("+type+")");
		Iterable<EvenementEntity> itE = edao.findByType(type);
		Iterator<EvenementEntity> it = itE.iterator();
		List<EvenementDto> list = new ArrayList<>(); 
		while(it.hasNext()) {
			EvenementEntity ee = it.next();
			UtilisateurSimpleDto user = UtilisateurSimpleDto.builder().matricule(ee.getUser().getMatricule())
					.nom(ee.getUser().getNom()).prenom(ee.getUser().getPrenom()).dateDeNaissance(ee.getUser().getDateDeNaissance())
					.salaire(ee.getUser().getSalaire()).mail(ee.getUser().getMail()).tel(ee.getUser().getTel())
					.responsable(ee.getUser().isResponsable()).build();
			
			//A MODIFIER POUR INCLURE L'UTILISATEUR
			list.add(new EvenementDto(ee.getId(),
						ee.getTitre(),
						ee.getType(),
						ee.getDescription(),
						ee.getDate_debut(),
						ee.getDate_fin(),
						ee.getDuree(),
						user
					));
		}
		
		if(list.size()==0) list = null;
		
		return list;
	}

	/**
	 * Ajoute en base de donnée l'évènement placé en paramètre 
	 * et retourne ce dernier tel qu'il est en base de donnée
	 * @param evenement de type @see EvenementDto
	 * @return evenement de type @see EvenementDto
	 */
	@Override
	public EvenementDto create(EvenementDto evenement) {
		log.info("Ajout d'un évènement en base de donnée : "+evenement);

		UtilisateurEntity ue = udao.findById(evenement.getUser().getMatricule()).get();
		
		EvenementEntity ee = EvenementEntity.builder() 
				.titre(evenement.getTitre())
				.type(evenement.getType())
				.description(evenement.getDescription())
				.date_debut(evenement.getDate_debut())
				.date_fin(evenement.getDate_fin())
				.duree(evenement.getDuree())
				.user(ue)
				.build();
		
		ee = edao.save(ee);
		UtilisateurSimpleDto user = UtilisateurSimpleDto.builder().matricule(ee.getUser().getMatricule())
				.nom(ee.getUser().getNom()).prenom(ee.getUser().getPrenom()).dateDeNaissance(ee.getUser().getDateDeNaissance())
				.salaire(ee.getUser().getSalaire()).mail(ee.getUser().getMail()).tel(ee.getUser().getTel())
				.responsable(ee.getUser().isResponsable()).build();
		EvenementDto edto = EvenementDto.builder()
								.id(ee.getId())
								.titre(ee.getTitre())
								.type(ee.getType())
								.description(ee.getDescription())
								.date_debut(ee.getDate_debut())
								.date_fin(ee.getDate_fin())
								.duree(ee.getDuree())
								.user(user)
								.build();
		return edto;
	}

	/**
	 * Met à jour la base de donnée à partir de l'évènement placé en paramètre
	 * et retourne ce dernier tel qu'il est en base de donnée
	 * @param evenement de type @see EvenementDto
	 * @return evenement de type @see EvenementDto
	 */
	@Override
	public EvenementDto update(EvenementDto evenement) {
		log.info("Update d'un évènement en base de donnée"+evenement);

		UtilisateurEntity ue = udao.findById(evenement.getUser().getMatricule()).get();
		
		EvenementEntity ee = EvenementEntity.builder()
				.id(evenement.getId())
				.titre(evenement.getTitre())
				.type(evenement.getType())
				.description(evenement.getDescription())
				.date_debut(evenement.getDate_debut())
				.date_fin(evenement.getDate_fin())
				.duree(evenement.getDuree())
				.user(ue)
				.build();
		
		edao.save(ee);
		UtilisateurSimpleDto user = UtilisateurSimpleDto.builder().matricule(ee.getUser().getMatricule())
				.nom(ee.getUser().getNom()).prenom(ee.getUser().getPrenom()).dateDeNaissance(ee.getUser().getDateDeNaissance())
				.salaire(ee.getUser().getSalaire()).mail(ee.getUser().getMail()).tel(ee.getUser().getTel())
				.responsable(ee.getUser().isResponsable()).build();
		EvenementDto edto = EvenementDto.builder()
								.id(ee.getId())
								.titre(ee.getTitre())
								.type(ee.getType())
								.description(ee.getDescription())
								.date_debut(ee.getDate_debut())
								.date_fin(ee.getDate_fin())
								.duree(ee.getDuree())
								.user(user)
								.build();
		return edto;
	}

	/**
	 * Supprime l'évènement de la base de donnée à partir de
	 * l'évènement placé en paramètre 
	 * @param evenement de type @see EvenementDto
	 */
	@Override
	public void delete(EvenementDto evenement) {
		log.info("Supression d'un évènement en base de donnée"+evenement);
		
		UtilisateurEntity ue = udao.findById(evenement.getUser().getMatricule()).get();
		
		EvenementEntity ee = EvenementEntity.builder()
				.id(evenement.getId())
				.titre(evenement.getTitre())
				.type(evenement.getType())
				.description(evenement.getDescription())
				.date_debut(evenement.getDate_debut())
				.date_fin(evenement.getDate_fin())
				.duree(evenement.getDuree())
				.user(ue)
				.build();
		
		edao.delete(ee);
	}


	@Override
	public void deleteAll() {
		edao.deleteAll();
	}


}
