package fr.afpa.projetregistation.service.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.afpa.projetregistation.dao.IUtilisateurDao;
import fr.afpa.projetregistation.dto.UtilisateurDto;
import fr.afpa.projetregistation.dto.UtilisateurSimpleDto;
import fr.afpa.projetregistation.entity.UtilisateurEntity;
import fr.afpa.projetregistation.service.IUtilisateurService;
import fr.afpa.projetregistation.utils.Securite;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UtilisateurServiceImplTest {

	@Autowired
	IUtilisateurService utilisateurService;
	@Autowired
	IUtilisateurDao utilisateurDao;
	@Autowired
	BCryptPasswordEncoder monEncodeur; 
	
	/**
	 * Test la création de l'utilisateur, si bien associé à un couple connexion
	 * (matricule / password) Ainsi que la bonne adresse correspondante
	 */

	@Test
	@Order(1)
	void createUtilisateurTest() {
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date dateTest = null;
		try {
			dateTest = simpleDateFormat.parse("15-01-1989");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		UtilisateurDto employe = new UtilisateurDto("EMPTEST001", "pwd1", "nom1", "prenom1", dateTest, 2000.0,
				"mat@gmail.com", "06.06.06.06.06", false, 1, "rue test1", "complément test", "38000", "GRENOBLE",
				"France");

		UtilisateurDto responsable = new UtilisateurDto("RESPTEST001", "pwd2", "nom2", "prenom2", dateTest, 2000.0,
				"mat@gmail.com", "06.06.06.06.06", true, 2, "rue test2", "complément test", "69000", "LYON", "France");

		utilisateurService.create(employe);
		utilisateurService.create(responsable);

		Optional<UtilisateurEntity> optiUtilisateur = utilisateurDao.findById("EMPTEST001");
		UtilisateurEntity userTest = optiUtilisateur.get();
		assertNotNull(userTest);
		assertEquals("prenom1", userTest.getPrenom());
		assertEquals("nom1", userTest.getNom());
		assertEquals(dateTest, userTest.getDateDeNaissance());
		assertEquals(2000.0, userTest.getSalaire());
		assertEquals("mat@gmail.com", userTest.getMail());
		assertEquals("06.06.06.06.06", userTest.getTel());

		assertEquals(1, userTest.getAdresse().getNumero());
		assertEquals("rue test1", userTest.getAdresse().getRue());
		assertEquals("complément test", userTest.getAdresse().getComplement());
		assertEquals("38000", userTest.getAdresse().getCodePostal());
		assertEquals("GRENOBLE", userTest.getAdresse().getVille());
		assertEquals("France", userTest.getAdresse().getPays());

	}

	/**
	 * Test de le méthode getUtilisateurByMatricule pour récupérer un UtilisateurDto
	 * par son matricule préalablement créé Attention retour de la méthode
	 * UtilisateurDto
	 * 
	 * @param pMatricule String placé en paramètre pour récupérer l'Utilisateur
	 */

	@Test
	@Order(2)
	void getUtilisateurByMatriculeTest() {
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date dateTest = null;
		try {
			dateTest = simpleDateFormat.parse("15-01-1989");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		UtilisateurDto userTest = utilisateurService.getUtilisateurByMatricule("EMPTEST001");

		assertNotNull(userTest);
		assertEquals("prenom1", userTest.getPrenom());
		assertEquals("nom1", userTest.getNom());
		assertEquals(dateTest, userTest.getDateDeNaissance());
		assertEquals(2000.0, userTest.getSalaire());
		assertEquals("mat@gmail.com", userTest.getMail());
		assertEquals("06.06.06.06.06", userTest.getTel());

//		assertEquals(monEncodeur.encode("pwd1"), userTest.getPassword());

		assertEquals(1, userTest.getNumero());
		assertEquals("rue test1", userTest.getRue());
		assertEquals("complément test", userTest.getComplement());
		assertEquals("38000", userTest.getCodePostal());
		assertEquals("GRENOBLE", userTest.getVille());
		assertEquals("France", userTest.getPays());

	}

	/**
	 * Test de le méthode getUtilisateurByName pour récupérer un UtilisateurDto par
	 * son matricule, préalablement créé Attention retour de la méthode
	 * UtilisateurDto
	 * 
	 * @param pNom String placé en paramètre pour récupérer l'Utilisateur
	 */
	@Test
	@Order(3)
	void getUtilisateurByNameTest() {
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date dateTest = null;
		try {
			dateTest = simpleDateFormat.parse("15-01-1989");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		UtilisateurDto userTest = utilisateurService.getUtilisateurByName("nom1");

		assertNotNull(userTest);
		assertEquals("prenom1", userTest.getPrenom());
		assertEquals("nom1", userTest.getNom());
		assertEquals(dateTest, userTest.getDateDeNaissance());
		assertEquals(2000.0, userTest.getSalaire());
		assertEquals("mat@gmail.com", userTest.getMail());
		assertEquals("06.06.06.06.06", userTest.getTel());

//		assertEquals(monEncodeur.encode("pwd1"), userTest.getPassword());

		assertEquals(1, userTest.getNumero());
		assertEquals("rue test1", userTest.getRue());
		assertEquals("complément test", userTest.getComplement());
		assertEquals("38000", userTest.getCodePostal());
		assertEquals("GRENOBLE", userTest.getVille());
		assertEquals("France", userTest.getPays());

	}

	/**
	 * Permet de tester si TOUS les utilisateurs sont bien récupérés dans une liste
	 * de UtilisateurDto
	 * 
	 * 
	 */
	@Test
	@Order(4)
	void getAllUtilisateursTest() {

		List<UtilisateurSimpleDto> listeUsers = utilisateurService.getAllUtilisateurs(1);
		assertNotNull(listeUsers);
		assertNotEquals(0, listeUsers.size());
		assertNotNull(listeUsers.get(0));

		// CHECK SI DTO EST BIEN RECUP
		assertEquals("nomEMP", listeUsers.get(0).getNom());
		


	}

	/**
	 * Permet de tester si tous les EMPLOYES sont bien récupérés Seuls les
	 * Utilisateurs avec responsable = false sont récupérés
	 */
	@Test
	@Order(5)
	void getAllEmployesTest() {

		List<UtilisateurDto> listeEmployes = utilisateurService.getAllEmployes(1);

		assertNotNull(listeEmployes);
		assertNotEquals(0, listeEmployes.size());
		assertNotNull(listeEmployes.get(0));

		// CHECK SI DTO EST BIEN RECUP
//		assertEquals("nomEMP", listeEmployes.get(0).getNom());
		assertEquals(false, listeEmployes.get(0).isResponsable());
		assertEquals("rue de l'employé", listeEmployes.get(0).getRue());
//		assertEquals(Securite.hashMD5("pwd1"), listeEmployes.get(0).getPassword());

	}

	/**
	 * Permet de tester si tous les RESPONSABLES sont bien récupérés Seuls les
	 * Utilisateurs avec responsable = true sont récupérés
	 */
	@Test
	@Order(7)
	void getAllResponsablesTest() {

		List<UtilisateurDto> listeResponsables = utilisateurService.getAllResponsables(1);

		assertNotNull(listeResponsables);
		assertNotEquals(0, listeResponsables.size());
		assertNotNull(listeResponsables.get(0));

		// CHECK SI DTO EST BIEN RECUP
		assertEquals("nomRESP", listeResponsables.get(0).getNom());
		assertEquals(true, listeResponsables.get(0).isResponsable());
		assertEquals("rue du responsable", listeResponsables.get(0).getRue());


	}

//	UtilisateurDto employe = new UtilisateurDto("EMPTEST001", "pwd1", "nom1", "prenom1", dateTest, 2000.0,
//			"mat@gmail.com", "06.06.06.06.06", false, 1, "rue test1", "complément test", "38000", "GRENOBLE",
//			"France");
	
	@Test
	@Order(8)
	void updateUtilisateurTest() {
		UtilisateurDto userRecup = utilisateurService.getUtilisateurByMatricule("EMPTEST001");
		assertEquals("nom1" , userRecup.getNom());
		assertEquals("prenom1" , userRecup.getPrenom());
		
		System.out.println(userRecup);
		
		userRecup.setNom("nomModif");
		userRecup.setPrenom("prenomModif");
		
		utilisateurService.updateUtilisateur(userRecup);
		
		System.out.println(userRecup);
		
		assertEquals("nomModif" , userRecup.getNom());
		assertEquals("prenomModif" , userRecup.getPrenom());
	}
	
	/**
	 * Test de la suppresion d'un Utilisateur
	 * 
	 */
	@Test
	@Order(9)
	void deleteUtilisateurByMatriculeTest() {

		Optional<UtilisateurEntity> optiUtilisateur = utilisateurDao.findById("EMPTEST001");
		assertTrue(optiUtilisateur.isPresent());
		utilisateurService.deleteUtilisateurByMatricule("EMPTEST001");
		Optional<UtilisateurEntity> optiTest = utilisateurDao.findById("EMPTEST001");
		assertFalse(optiTest.isPresent());

		Optional<UtilisateurEntity> optiUtilisateur2 = utilisateurDao.findById("RESPTEST001");
		assertTrue(optiUtilisateur2.isPresent());
		utilisateurService.deleteUtilisateurByMatricule("RESPTEST001");
		Optional<UtilisateurEntity> optiTest2 = utilisateurDao.findById("RESPTEST001");
		assertFalse(optiTest2.isPresent());

	}
}
