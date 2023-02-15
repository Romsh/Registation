package fr.afpa.projetregistation.service.impl.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.afpa.projetregistation.dao.IUtilisateurDao;
import fr.afpa.projetregistation.dto.EvenementDto;
import fr.afpa.projetregistation.dto.UtilisateurSimpleDto;
import fr.afpa.projetregistation.entity.EvenementEntity;
import fr.afpa.projetregistation.entity.UtilisateurEntity;
import fr.afpa.projetregistation.service.IEvenementService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class EvenementServiceImplTest {
	
	@Autowired
	IEvenementService eserv;
	
	@Autowired
	private IUtilisateurDao udao;
	
	private static List<EvenementDto> list = new ArrayList<>();
	
	@Test
	@Order(1)
	public void testCreate() {
		String d1 = "30-08-2020";
		String d2 = "01-09-2020";
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date1 = null;
		Date date2 = null;
		int diff = 0;
		
		try {
			date1 = sdf.parse(d1);
			date2 = sdf.parse(d2);
			long mse = date2.getTime()-date1.getTime();
			diff = (int)TimeUnit.SECONDS.convert(mse, TimeUnit.MILLISECONDS); 
		}catch(ParseException e) {
			log.warn("Erreur lors du parsing des dates lors du test unitaire !");
		}

		
		Optional<UtilisateurEntity> optUser = udao.findById("EMP001");
		assertTrue(optUser.isPresent());

		if(optUser.isPresent()) {
			UtilisateurEntity user = optUser.get();

			UtilisateurSimpleDto user2 = UtilisateurSimpleDto.builder().matricule(user.getMatricule())
					.nom(user.getNom()).prenom(user.getPrenom()).dateDeNaissance(user.getDateDeNaissance())
					.salaire(user.getSalaire()).mail(user.getMail()).tel(user.getTel())
					.responsable(user.isResponsable()).build();
			
			EvenementDto ee = EvenementDto.builder().type("Panne")
					.description("test1")
					.date_debut(date1)
					.date_fin(date2)
					.duree(diff)
					.user(user2).build();
			
			assertNotNull(ee);
			
			ee = eserv.create(ee);
			
			assertNotNull(ee.getId());
			
			if(ee.getId()!=0) {
				EvenementDto optEE = eserv.getById(ee.getId());
				assertNotNull(optEE);
				list.add(optEE);
			}
		}
	}
	
	@Test
	@Order(2)
	public void testUpdate() {
		EvenementDto test2 = eserv.getById(list.get(0).getId());
		test2.setType("Autre");
		eserv.update(test2);

		EvenementDto edto = eserv.getById(test2.getId());
		
		assertNotNull(edto);
		assertNotEquals("Panne", edto.getType());
		list.remove(0);
		list.add(test2);
	}
	
	@Test
	@Order(3)
	public void testReadByDateIntervale() {
			String d1 = "30-08-2020";
			String d2 = "01-09-2020";
			String pattern = "dd-MM-yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date date1 = null;
			Date date2 = null;
			
			try {
				date1 = sdf.parse(d1);
				date2 = sdf.parse(d2);
			}catch(ParseException e) {
				log.warn("Erreur lors du parsing des dates lors du test unitaire !");
			}
			
			List<EvenementDto> le = null;
			le = eserv.getByDate(date1, date2,"EMP001");
			assertEquals(2,le.size());
			if(le.size()==1) {
				assertEquals(date2,le.get(0).getDate_fin());
			}
	}
	
	@Test
	@Order(4)
	public void testReadByDate() {
		String d1 = "30-08-2020";
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date1 = null;
		
		try {
			date1 = sdf.parse(d1);
		}catch(ParseException e) {
			log.warn("Erreur lors du parsing des dates lors du test unitaire !");
		}
		
		List<EvenementDto> le = null;
		le = eserv.getByDate(date1);
		assertEquals(3,le.size());
		if(le.size()==1) {
			assertEquals(date1,le.get(0).getDate_debut());
		}
	}
	
	@Test
	@Order(5)
	public void testReadByType() {
		List<EvenementDto> le = null;
		le = eserv.getByType("autre");
		assertEquals(1,le.size());

		le = null;
		le = eserv.getByType("panne");
		assertNull(le);
	}
	
	@Test
	@Order(6)
	public void testReadById() {
		EvenementDto edto = eserv.getById(list.get(0).getId());
		assertNotNull(edto);
	}
	
	@Test
	@Order(7)
	public void testDelete() {
		EvenementDto edto = eserv.getById(list.get(0).getId());
		assertNotNull(edto);
		
		eserv.delete(edto);
		edto = eserv.getById(list.get(0).getId());
		assertNull(edto);
		
		list.remove(0);
	}
	
	@Test
	@Order(8)
	public void testDeleteAll() {
		eserv.deleteAll();
		assertNull(eserv.getAll());
	}
}
