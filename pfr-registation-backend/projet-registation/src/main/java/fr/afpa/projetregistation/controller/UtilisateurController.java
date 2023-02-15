package fr.afpa.projetregistation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.projetregistation.dao.IUtilisateurDao;
import fr.afpa.projetregistation.dto.MaterielDto;
import fr.afpa.projetregistation.dto.MessageContactDto;
import fr.afpa.projetregistation.dto.UtilisateurDto;
import fr.afpa.projetregistation.dto.UtilisateurSimpleDto;
import fr.afpa.projetregistation.entity.UtilisateurEntity;
import fr.afpa.projetregistation.service.IUtilisateurService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {

	@Autowired
	IUtilisateurDao utilisateurDao;

	@Autowired
	IUtilisateurService utilisateurService;

	@GetMapping("/utilisateur")
    public List<UtilisateurSimpleDto> findAll() {
        return (List<UtilisateurSimpleDto>) utilisateurService.getAllUtilisateurs(1);
    }

	@PostMapping("/utilisateur")
    public void addUser(@RequestBody UtilisateurDto pUtilisateur) {
		utilisateurService.create(pUtilisateur);       
    }	
	
	@DeleteMapping("/utilisateur/{matriculeToDelete}")
	public void supprimerUtilisateur(@PathVariable (value="matriculeToDelete") String pMatriculeToDelete) {
		utilisateurService.deleteUtilisateurByMatricule(pMatriculeToDelete);
	}
	
	@PostMapping("/utilisateur/contact")
	public void contactUs(@RequestBody MessageContactDto pMessage) {
		utilisateurService.contactUs(pMessage);
	}
	
	@PostMapping("/utilisateur/mat")
	public UtilisateurSimpleDto findByMatricuel(@RequestBody String pMatricule) {
		return utilisateurService.getUtilisateurSimpleByMatricule(pMatricule);
	}
	
	
}
