package fr.afpa.projetregistation.service.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.afpa.projetregistation.dao.IDocumentDao;
import fr.afpa.projetregistation.dao.IUtilisateurDao;
import fr.afpa.projetregistation.dto.DocumentDto;
import fr.afpa.projetregistation.entity.UtilisateurEntity;
import fr.afpa.projetregistation.service.IDocumentService;
import fr.afpa.projetregistation.service.IUtilisateurService;
import fr.afpa.projetregistation.utils.Constantes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestDocumentServiceImpl {

	@Autowired
	private IDocumentService documentService;
	
	@Autowired
	private UtilisateurEntity utilisateurEntity;

	private DocumentDto document = DocumentDto.builder().nomDocument(Constantes.STRING_TEST)
			.categorieDocument(Constantes.STRING_TEST).dateAjoutDocument(Constantes.DATE_TEST)
			.dateDerniereModificationDocument(Constantes.DATE_TEST).descriptionDocument(Constantes.STRING_TEST)
			.commentairesDocument(Constantes.STRING_TEST).build();

	@Test
	@Order(1)
	public void testAjouterDocument() throws Exception {

		document = documentService.ajouterDocument(document);
		
		
		assertNotNull(document);
		assertEquals(Constantes.STRING_TEST, document.getNomDocument());
		assertEquals(Constantes.STRING_TEST, document.getCategorieDocument());
		assertEquals(Constantes.DATE_TEST, document.getDateAjoutDocument());
		assertEquals(Constantes.DATE_TEST, document.getDateDerniereModificationDocument());
		assertEquals(Constantes.STRING_TEST, document.getDescriptionDocument());
		assertEquals(Constantes.STRING_TEST, document.getCommentairesDocument());

	}

	@Test
	@Order(2)

	public void testGetDocument() throws Exception {

		document = documentService.getDocument(documentService.getMaxid());
		assertNotNull(document);

	}

	@Test
	@Order(3)
	public void testGetDocumentByNom() throws Exception {

		int index = documentService.getMaxid();
		documentService.getDocumentByNom(Constantes.STRING_TEST);
		document = documentService.getDocument(index);

		assertEquals(Constantes.STRING_TEST, document.getNomDocument());

	}

	@Test
	@Order(4)

	public void testMajNomDocument() throws Exception {

		int index = documentService.getMaxid();
		documentService.majnomDocument(Constantes.STRING_TEST2, index);
		document = documentService.getDocument(index);

		assertEquals(Constantes.STRING_TEST2, document.getNomDocument());

	}

	@Test
	@Order(5)
	public void testMajDateAjoutDocument() throws Exception {

		Date date2 = new Date();
		int index = documentService.getMaxid();
		documentService.majDateAjoutDocument(date2, index);
		document = documentService.getDocument(index);
		assertNotNull(document);

		assertEquals(date2, document.getDateAjoutDocument());

	}

	@Test
	@Order(6)
	public void testMajDateDerniereModificationDocument() throws Exception {

		Date date3 = new Date();
		int index = documentService.getMaxid();
		documentService.majDateDerniereModificationDocument(date3, index);
		document = documentService.getDocument(index);
		assertNotNull(document);

		assertEquals(date3, document.getDateDerniereModificationDocument());

	}

	@Test
	@Order(7)
	public void testMajDescriptionDocument() throws Exception {

		int index = documentService.getMaxid();
		documentService.majDescriptionDocument(Constantes.STRING_TEST2, index);
		document = documentService.getDocument(index);

		assertEquals(Constantes.STRING_TEST2, document.getDescriptionDocument());

	}

	@Test
	@Order(8)
	public void testMajCommentairesDocument() throws Exception {

		int index = documentService.getMaxid();
		documentService.majCommentairesDocument(Constantes.STRING_TEST2, index);
		document = documentService.getDocument(index);

		assertEquals(Constantes.STRING_TEST2, document.getCommentairesDocument());

	}

	@Test
	@Order(9)

	public void testMajCategorieDocument() throws Exception {

		int index = documentService.getMaxid();
		documentService.majCategorieDocument(Constantes.STRING_TEST2, index);
		document = documentService.getDocument(index);

		assertEquals(Constantes.STRING_TEST2, document.getCategorieDocument());

	}

	@Test
	@Order(10)
	public void testGetAllDocuments() throws Exception {

		List<DocumentDto> listeDocs = documentService.getAllDocuments(2);

		assertNotNull(listeDocs);

		log.info(listeDocs.toString());

		assertNotEquals(0, listeDocs.size());
	}

	@Test
	@Order(11)
	public void testExistById() throws Exception {

		assertTrue(documentService.existById(documentService.getMaxid()));
	}

	@Test
	@Order(12)
	public void testSupprimerDocument() throws Exception {

		int index = documentService.getMaxid();

		documentService.supprimerDocument(index);

		document = documentService.getDocument(index);

		assertNull(document);

	}

}