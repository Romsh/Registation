package fr.afpa.projetregistation.service.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.afpa.projetregistation.dao.IMaterielDao;
import fr.afpa.projetregistation.dto.MaterielDto;
import fr.afpa.projetregistation.entity.MaterielEntity;
import fr.afpa.projetregistation.service.IMaterielService;
import fr.afpa.projetregistation.utils.Constantes;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestMaterielServiceImpl {

	@Autowired
	private IMaterielService matService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	IMaterielDao materielDao;

	private Date date = new Date();

	private MaterielDto mat = new MaterielDto("P004", "Orion", "PistoXC", 500,  1, nouvelleDate(), "POMPE", "pompe2");
	private MaterielDto mat3 = new MaterielDto(5, "MC001", "Pluton", "Cafe3000", 200, "allée 1", 1, nouvelleDate(),
			"MACHINE A CAFE");

	public Date nouvelleDate() {
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse("15-01-1989");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Test
	@Order(1)
	/**
	 * Teste le bon ajout d'un MaterielDto par le MaterielServiceImpl
	 *
	 * @throws Exception
	 */
	public void testAddMateriel() throws Exception {

		mat = matService.create(mat);
		mat = matService.getMaterielById(6);

		assertNotNull(mat);
		assertEquals("Orion", mat.getMarque());
		assertEquals("PistoXC", mat.getModele());
		assertEquals(500, mat.getPrix());
		assertEquals("pompe2", mat.getLocalisation());
		assertEquals(1, mat.getEtat());
		assertEquals(date, date);
		assertEquals("POMPE", mat.getTypeMateriel());

	}
 
	/**
	 * Teste la bonne récupération de tous les MaterielDto par le
	 * MaterielServiceImpl
	 *
	 * @throws Exception
	 */
	@Test
	@Order(2)
	public void testGetAll() throws Exception {

		List<MaterielDto> listeMat = matService.getAll(Constantes.ELEMENTS_PAGE);
		assertNotNull(listeMat);

		List<MaterielDto> liste2 = new ArrayList<MaterielDto>();
		for (int i = 0; i < 3; i++) {
			MaterielEntity mat = new MaterielEntity();
			liste2.add(this.modelMapper.map(mat, MaterielDto.class));
		}

		assertNotNull(liste2);

	}

	/**
	 * Teste la bonne récupération de tous les MaterielDto par le
	 * MaterielServiceImpl
	 *
	 * @throws Exception
	 */
	@Test
	@Order(3)
	public void testGetAllByType() throws Exception {

		List<MaterielDto> listeMat = matService.getAllByType(2, "CUVE");
		assertNotNull(listeMat);

		for (MaterielDto materielDto : listeMat) {
			assertEquals("CUVE", materielDto.getTypeMateriel());
		}

	}

	/**
	 * Teste la bonne récupération d'un MaterielDto par le MaterielServiceImpl via
	 * la ref.
	 *
	 * @throws Exception
	 */
	@Test
	@Order(4)
	public void testGetMaterielByRef() throws Exception {

		MaterielDto materiel = matService.getMaterielByRef("P001");

		assertNotNull(materiel);
		assertEquals("P001", materiel.getRef());
		assertEquals("Orion", materiel.getMarque());
		assertEquals("PistoXC", materiel.getModele());
		assertEquals(500, materiel.getPrix());
		assertEquals("pompe2", materiel.getLocalisation());
		assertEquals(1, materiel.getEtat());
		assertEquals(date, date);
		assertEquals("POMPE", materiel.getTypeMateriel());

	}

	/**
	 * Teste la bonne mise à jour d'information d'un matériel.
	 *
	 * @throws Exception
	 */
	@Test
	@Order(5)
	public void testUpdateByRef() throws Exception {

		matService.updateByRef("MC001", mat3);
		MaterielDto matDto = matService.getMaterielByRef("MC001");

		assertNotNull(matDto);
		assertEquals("MC001", matDto.getRef());
		assertEquals("Pluton", matDto.getMarque());
		assertEquals("Cafe3000", matDto.getModele());
		assertEquals(200, matDto.getPrix());
		assertEquals("allée 1", matDto.getLocalisation());
		assertEquals(1, matDto.getEtat());
		assertEquals(date, date);

	}

	/**
	 * Teste la bonne mise à jour de l'état d'un matériel.
	 *
	 * @throws Exception
	 */
	@Test
	@Order(6)
	public void testUpdateEtatByRef() throws Exception {

		matService.updateEtatByRef("P002", 3);
		MaterielDto matDto = matService.getMaterielByRef("P002");

		assertNotNull(matDto);
		assertEquals("P002", matDto.getRef());
		assertEquals("Orion", matDto.getMarque());
		assertEquals("PistoXC", matDto.getModele());
		assertEquals(500, matDto.getPrix());
		assertEquals("pompe2", matDto.getLocalisation());
		assertEquals(3, matDto.getEtat());
		assertEquals(this.nouvelleDate(), matDto.getDateAchat());
		assertEquals("POMPE", matDto.getTypeMateriel());

	}

	@Test
	@Order(7)
	public void testDeleteByRef() throws Exception {

		matService.deleteByRef("C001");
		Optional<MaterielEntity> opRes = materielDao.findByRef("C001");
		MaterielEntity matEntity = null;
		if (opRes.isPresent()) {
			matEntity = opRes.get();
		}
		assertNull(matEntity);
	}

//	@Test
//	@Order(8)
//	public void testDeleteAllByType() throws Exception {
//
//		matService.deleteAllByType(2, "MACHINE A CAFE");
//		List<MaterielDto> liste = matService.getAllByType(2, "MACHINE A CAFE");
//		assertNotNull(liste);
//
//		for (MaterielDto materielDto : liste) {
//			assertEquals("MACHINE A CAFE", materielDto.getTypeMateriel());
//		}
//
//	}
}
