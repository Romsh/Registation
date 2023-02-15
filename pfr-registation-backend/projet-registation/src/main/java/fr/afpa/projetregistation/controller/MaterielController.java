package fr.afpa.projetregistation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.projetregistation.dto.MaterielDto;
import fr.afpa.projetregistation.service.IMaterielService;
import fr.afpa.projetregistation.service.ITypeMaterielService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class MaterielController {

	@Autowired
	IMaterielService materielService;

	@Autowired
	ITypeMaterielService typeService;

	@GetMapping("/materiel/liste/{pPage}")
	public List<MaterielDto> getAll(@PathVariable(value = "pPage") int pageParam) {

		int pageEnCours = 1;

		if (pageParam != 0) {
			try {
				pageEnCours = pageParam;
				if (pageEnCours < 1) {
					pageEnCours = 1;
				}
			} catch (NumberFormatException e) {
				System.err.println("attention : " + e.getMessage());
			}
		}
		List<MaterielDto> listeMat = new ArrayList<MaterielDto>();
		listeMat = materielService.getAll(pageEnCours);
		return listeMat;
	}

	@GetMapping("/materiel/listeType/{pType}/{pPage}")
	public List<MaterielDto> getAllByType(@PathVariable(value = "pType") String pType,
			@PathVariable(value = "pPage") int pageParam) {

		pType= pType.toUpperCase();
		int pageEnCours = 1;

		if (pageParam != 0) {
			try {
				pageEnCours = pageParam;
				if (pageEnCours < 1) {
					pageEnCours = 1;
				}
			} catch (NumberFormatException e) {
				System.err.println("attention : " + e.getMessage());
			}
		}
		List<MaterielDto> listeMat = new ArrayList<MaterielDto>();
		listeMat = materielService.getAllByType(pageEnCours, pType);
		return listeMat;
	}

	@GetMapping("/materiel/{pId}")
	public MaterielDto getById(@PathVariable(value = "pId") int pId) {

		MaterielDto mat = materielService.getMaterielById(pId);

		return mat;
	}

	@PostMapping("/materiel")
	public void addMateriel(@RequestBody MaterielDto pMat) {
		materielService.create(pMat);
	}

	@DeleteMapping("/materiel/{pId}")
	public void deleteMateriel(@PathVariable(value = "pId") int pId) {
		materielService.deleteById(pId);
	}

	@PatchMapping("/materiel/update")
	public void updateMateriel(@RequestBody MaterielDto pMat) {
		System.out.println(pMat);
		materielService.updateByRef(pMat.getRef(), pMat);
	}

}
