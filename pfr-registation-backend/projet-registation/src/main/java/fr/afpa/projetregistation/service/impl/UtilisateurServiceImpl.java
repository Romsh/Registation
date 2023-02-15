package fr.afpa.projetregistation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.afpa.projetregistation.dao.IAdresseDao;
import fr.afpa.projetregistation.dao.IConnexionDao;
import fr.afpa.projetregistation.dao.IUtilisateurDao;
import fr.afpa.projetregistation.dto.MessageContactDto;
import fr.afpa.projetregistation.dto.UtilisateurDto;
import fr.afpa.projetregistation.dto.UtilisateurSimpleDto;
import fr.afpa.projetregistation.entity.AdresseEntity;
import fr.afpa.projetregistation.entity.ConnexionEntity;
import fr.afpa.projetregistation.entity.UtilisateurEntity;
import fr.afpa.projetregistation.service.IUtilisateurService;
import fr.afpa.projetregistation.utils.Constantes;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe qui implémente l'interface IUtilisateurService et permet de définir le
 * métier, les méthodes de l'utilisateur
 *
 * @author Mathieu
 * @version 1.0
 */

@Slf4j
@Service
public class UtilisateurServiceImpl implements IUtilisateurService {

	/**
	 * Instanciation de l'interface IAdresseDao
	 */
	@Autowired
	IAdresseDao adresseDao;

	/**
	 * Instanciation de l'interface IConnexion
	 */
	@Autowired
	IConnexionDao connexionDao;

	/**
	 * Instanciation de l'interface IUtilisateurDao
	 */
	@Autowired
	IUtilisateurDao utilisateurDao;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	PasswordEncoder monEncodeur;

	/**
	 * Crée un UtilisateurDto et sauvegarde un utilisateur en BDD en utilisant
	 * l'UtilisateurDao.
	 *
	 * @see
	 * @param UtilisateurDao
	 * @return UtilisateurDao
	 */

	@Override
	public UtilisateurDto create(UtilisateurDto pUtilisateur) {
		UtilisateurEntity utilisateur = new UtilisateurEntity();
		String matricule = pUtilisateur.getMatricule();
		String password = pUtilisateur.getPassword();

		Optional<ConnexionEntity> optiConnexion = connexionDao.findByMatricule(matricule);
		if (optiConnexion.isPresent()) {
			log.warn("Ajout UTILISATEUR impossible - le matricule existe déjà");
			return null;
		} else {
			ConnexionEntity coupleConnexion = new ConnexionEntity(matricule, monEncodeur.encode(password));
			connexionDao.save(coupleConnexion);

			AdresseEntity adresse = AdresseEntity.builder().numero(pUtilisateur.getNumero()).rue(pUtilisateur.getRue())
					.complement(pUtilisateur.getComplement()).codePostal(pUtilisateur.getCodePostal())
					.ville(pUtilisateur.getVille()).pays(pUtilisateur.getPays()).build();
			adresseDao.save(adresse);

			utilisateur = UtilisateurEntity.builder().matricule(matricule).prenom(pUtilisateur.getPrenom())
					.nom(pUtilisateur.getNom()).dateDeNaissance(pUtilisateur.getDateDeNaissance())
					.salaire(pUtilisateur.getSalaire()).mail(pUtilisateur.getMail()).tel(pUtilisateur.getTel())
					.responsable(pUtilisateur.isResponsable()).connexion(coupleConnexion).adresse(adresse).build();
			utilisateurDao.save(utilisateur);
			log.info("UTILISATEUR ajouté avec succès");
			return pUtilisateur;
		}

	}

	/**
	 * Supprime un Utilisateur récupéré de la BDD en utilisant le matricule (unique)
	 * de l'utilisateur à supprimer.
	 *
	 * Utilisation de l'Optional, test d'abord si l'utilisateur est bien présent en
	 * BDD.
	 *
	 * @see
	 * @param pMatricule String utilisé pour récupérer l'utilisateur
	 *
	 */
	@Override
	public void deleteUtilisateurByMatricule(String pMatricule) {
		Optional<UtilisateurEntity> optiUtilisateur = utilisateurDao.findById(pMatricule);
		if (!optiUtilisateur.isPresent()) {
			log.warn("UTILISATEUR delete - impossible de supprimer une personne qui n'existe pas !");
		} else {
			utilisateurDao.deleteById(pMatricule);
		}
	}

	@Override
	public void updateUtilisateur(UtilisateurDto pUtilisateur) {
		Optional<UtilisateurEntity> optiUtilisateur = utilisateurDao.findById(pUtilisateur.getMatricule());
		UtilisateurEntity user = null;
		if (!optiUtilisateur.isPresent()) {
			log.warn("UTILISATEUR - Update impossible ce matricule n'existe pas !");
		} else {
			user = optiUtilisateur.get();
			user = UtilisateurEntity.builder().matricule(pUtilisateur.getMatricule()).prenom(pUtilisateur.getPrenom())
					.nom(pUtilisateur.getNom()).dateDeNaissance(pUtilisateur.getDateDeNaissance())
					.salaire(pUtilisateur.getSalaire()).mail(pUtilisateur.getMail()).tel(pUtilisateur.getTel())
					.responsable(pUtilisateur.isResponsable())
					.adresse(AdresseEntity.builder().rue(pUtilisateur.getRue()).complement(pUtilisateur.getComplement())
							.codePostal(pUtilisateur.getCodePostal()).ville(pUtilisateur.getVille())
							.pays(pUtilisateur.getPays())

							.build())
					.build();
			utilisateurDao.save(user);
		}

	}

	/**
	 * Permet de récupérer un Utilisateur par son matricule.
	 * 
	 * @param pMatricule String utilisé pour récupérer l'utilisateur
	 * 
	 */
	@Override
	public UtilisateurDto getUtilisateurByMatricule(String pMatricule) {
		Optional<UtilisateurEntity> optiUtilisateur = utilisateurDao.findById(pMatricule);
		UtilisateurDto userDto = null;
		if (!optiUtilisateur.isPresent()) {
			log.info("UTILISATEUR findByMatricule - Cette personne n'existe pas");
		} else {
			UtilisateurEntity utilisateur = optiUtilisateur.get();
			userDto = modelMapper.map(utilisateur, UtilisateurDto.class);
			userDto.setPassword(utilisateur.getConnexion().getPassword());
			userDto.setNumero(utilisateur.getAdresse().getNumero());
			userDto.setRue(utilisateur.getAdresse().getRue());
			userDto.setComplement(utilisateur.getAdresse().getComplement());
			userDto.setCodePostal(utilisateur.getAdresse().getCodePostal());
			userDto.setVille(utilisateur.getAdresse().getVille());
			userDto.setPays(utilisateur.getAdresse().getPays());

			log.debug("UTILISATEUR findByMatricule - Utilisateur récup --> " + userDto);
		}
		return userDto;
	}

	/**
	 * Permet de récupérer un Utilisateur par son nom. Récupération d'un optional et
	 * construction du dto
	 * 
	 * @param pNom String utilisé pour récupérer l'utilisateur
	 * @return UtilisateurDto
	 */
	@Override
	public UtilisateurDto getUtilisateurByName(String pNom) {
		Optional<UtilisateurEntity> optiUtilisateur = utilisateurDao.findByNom(pNom);

		UtilisateurDto userDto = null;
		if (!optiUtilisateur.isPresent()) {
			log.info("UTILISATEUR findByMatricule - Cette personne n'existe pas");
		} else {
			UtilisateurEntity utilisateur = optiUtilisateur.get();
			userDto = modelMapper.map(utilisateur, UtilisateurDto.class);
			userDto.setPassword(utilisateur.getConnexion().getPassword());
			userDto.setNumero(utilisateur.getAdresse().getNumero());
			userDto.setRue(utilisateur.getAdresse().getRue());
			userDto.setComplement(utilisateur.getAdresse().getComplement());
			userDto.setCodePostal(utilisateur.getAdresse().getCodePostal());
			userDto.setVille(utilisateur.getAdresse().getVille());
			userDto.setPays(utilisateur.getAdresse().getPays());

			log.info("UTILISATEUR findByMatricule - Utilisateur récup --> " + userDto);
		}
		return userDto;
	}

	/**
	 * Retourne la liste de tous les utilisateurs avec pagination les champs
	 * manquants de l'utilisateurDto sont set
	 * 
	 * @param pPageEnCours int correspondant à la page en cours
	 * @return List de UtilisateurDto
	 */
//	@Override
//	public List<UtilisateurDto> getAllUtilisateurs(int pPageEnCours) {
//		List<UtilisateurDto> listeUtilisateurs = new ArrayList<>();
//		PageRequest page = PageRequest.of(pPageEnCours - 1, Constantes.ELEMENTS_PAR_PAGE);
//		Page<UtilisateurEntity> listeUsers = this.utilisateurDao.findAll(page);
//		UtilisateurDto userDto = new UtilisateurDto();
//		for (UtilisateurEntity utilisateurEntity : listeUsers) {
//			userDto = this.modelMapper.map(utilisateurEntity, UtilisateurDto.class);
//			userDto.setPassword(utilisateurEntity.getConnexion().getPassword());
//			userDto.setNumero(utilisateurEntity.getAdresse().getNumero());
//			userDto.setRue(utilisateurEntity.getAdresse().getRue());
//			userDto.setComplement(utilisateurEntity.getAdresse().getComplement());
//			userDto.setCodePostal(utilisateurEntity.getAdresse().getCodePostal());
//			userDto.setVille(utilisateurEntity.getAdresse().getVille());
//			userDto.setPays(utilisateurEntity.getAdresse().getPays());
//			
//			listeUtilisateurs.add(userDto);
//
//		}
//		return listeUtilisateurs;
//	}

	// Testouille
	@Override
	public List<UtilisateurSimpleDto> getAllUtilisateurs(int pPageEnCours) {
		List<UtilisateurSimpleDto> listeUtilisateurs = new ArrayList<>();
		PageRequest page = PageRequest.of(pPageEnCours - 1, Constantes.ELEMENTS_PAR_PAGE);
		Page<UtilisateurEntity> listeUsers = this.utilisateurDao.findAll(page);
		UtilisateurSimpleDto userDto = new UtilisateurSimpleDto();
		for (UtilisateurEntity utilisateurEntity : listeUsers) {
			userDto = this.modelMapper.map(utilisateurEntity, UtilisateurSimpleDto.class);
			userDto.setMatricule(utilisateurEntity.getMatricule());
			userDto.setNom(utilisateurEntity.getNom());
			userDto.setPrenom(utilisateurEntity.getPrenom());
			userDto.setDateDeNaissance(utilisateurEntity.getDateDeNaissance());
			userDto.setSalaire(utilisateurEntity.getSalaire());
			userDto.setMail(utilisateurEntity.getMail());
			userDto.setTel(utilisateurEntity.getTel());
			userDto.setResponsable((utilisateurEntity.isResponsable()));

			listeUtilisateurs.add(userDto);

		}
		return listeUtilisateurs;
	}

	/**
	 * Permet de retourner une liste de tous les emlpoyés (boolean responsable =
	 * false) Utilisation du Page, liste avec pagination
	 * 
	 * @return List de UtilisateurEntity qui sont des employés
	 */
	@Override
	public List<UtilisateurDto> getAllEmployes(int pPageEnCours) {
		List<UtilisateurDto> listeEmployes = new ArrayList<>();
		PageRequest page = PageRequest.of(pPageEnCours - 1, Constantes.ELEMENTS_PAR_PAGE);
		Page<UtilisateurEntity> listeUsers = this.utilisateurDao.findAllEmployes(page);
		UtilisateurDto userDto = new UtilisateurDto();
		for (UtilisateurEntity utilisateurEntity : listeUsers) {
			userDto = this.modelMapper.map(utilisateurEntity, UtilisateurDto.class);
			userDto.setPassword(utilisateurEntity.getConnexion().getPassword());
			userDto.setNumero(utilisateurEntity.getAdresse().getNumero());
			userDto.setRue(utilisateurEntity.getAdresse().getRue());
			userDto.setComplement(utilisateurEntity.getAdresse().getComplement());
			userDto.setCodePostal(utilisateurEntity.getAdresse().getCodePostal());
			userDto.setVille(utilisateurEntity.getAdresse().getVille());
			userDto.setPays(utilisateurEntity.getAdresse().getPays());

			listeEmployes.add(userDto);

		}
		return listeEmployes;
	}

	/**
	 * Permet de retourner une liste de tous les responsables (boolean responsable =
	 * true) Utilisation du Page, liste avec pagination
	 * 
	 * @return List de UtilisateurEntity qui sont des responsables
	 */
	@Override
	public List<UtilisateurDto> getAllResponsables(int pPageEnCours) {
		List<UtilisateurDto> listeResponsables = new ArrayList<>();
		PageRequest page = PageRequest.of(pPageEnCours - 1, Constantes.ELEMENTS_PAR_PAGE);
		Page<UtilisateurEntity> listeUsers = this.utilisateurDao.findAllResponsables(page);
		UtilisateurDto userDto = new UtilisateurDto();
		for (UtilisateurEntity utilisateurEntity : listeUsers) {
			userDto = this.modelMapper.map(utilisateurEntity, UtilisateurDto.class);
			userDto.setPassword(utilisateurEntity.getConnexion().getPassword());
			userDto.setNumero(utilisateurEntity.getAdresse().getNumero());
			userDto.setRue(utilisateurEntity.getAdresse().getRue());
			userDto.setComplement(utilisateurEntity.getAdresse().getComplement());
			userDto.setCodePostal(utilisateurEntity.getAdresse().getCodePostal());
			userDto.setVille(utilisateurEntity.getAdresse().getVille());
			userDto.setPays(utilisateurEntity.getAdresse().getPays());

			listeResponsables.add(userDto);

		}
		return listeResponsables;
	}

	@Override
	public boolean authentification(String login, String motdepasse) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void contactUs(MessageContactDto pMessage) {
		log.info("---->Début envoi mail");
		// Etape 1 envoi mail à l'appli
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("registationcdatest@gmail.com"); // Impossible de modifier celui qui envoie
		message.setTo("registationcdatest@gmail.com"); // L'appli reçoit le message

		String mailSubject = "Nouvelle demande de contact";
		String mailContent = "Voici le message envoyé de : " + "\n" + "Nom : " + pMessage.getNom() + "\n" + "Prénom : "
				+ pMessage.getPrenom() + "\n" + "Email : " + pMessage.getEmail() + "\n" + "Message : " + "\n"
				+ pMessage.getMessage() + "\n" + "Fin du message.";

		message.setSubject(mailSubject);
		message.setText(mailContent);

		mailSender.send(message);

		log.info("-------->mail 1 envoyé");

		// Etape2 envoi du mail auto au demandeur
		message = new SimpleMailMessage();
		message.setFrom("registationcdatest@gmail.com"); //
		message.setTo(pMessage.getEmail());

		mailSubject = "Demande de contact bien reçue";
		mailContent = "Bonjour, " + "\n"
				+ "Nous avons bien reçue votre demande, elle sera traitée dans les meilleurs délais." + "\n"
				+ "L'équipe RegiStation vous remercie. ";

		message.setSubject(mailSubject);
		message.setText(mailContent);

		mailSender.send(message);

		log.info("-------->mail 2 envoyé");
		log.info("---->FIN envoi mail");
	}

	@Override
	public UtilisateurSimpleDto getUtilisateurSimpleByMatricule(String pMatricule) {
		Optional<UtilisateurEntity> optiUtilisateur = utilisateurDao.findById(pMatricule);
		UtilisateurSimpleDto userSimpleDto = null;
		if (!optiUtilisateur.isPresent()) {
			log.info("UTILISATEUR findByMatricule - Cette personne n'existe pas");
		} else {
			UtilisateurEntity utilisateur = optiUtilisateur.get();
			userSimpleDto = modelMapper.map(utilisateur, UtilisateurSimpleDto.class);
			log.debug("UTILISATEUR findByMatricule - Utilisateur récup --> " + userSimpleDto);
		}
		return userSimpleDto;
	}

}
