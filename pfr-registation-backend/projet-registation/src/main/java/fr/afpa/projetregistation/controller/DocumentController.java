package fr.afpa.projetregistation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.projetregistation.dto.DocumentDto;
import fr.afpa.projetregistation.service.IDocumentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class DocumentController {

	@Autowired
	IDocumentService documentService;

	@GetMapping("/document")
    public List<DocumentDto> getDocuments() {
		List<DocumentDto> documentsListe = documentService.getAllDocuments(1);
        return documentsListe;
    }

    @PostMapping("/document")
    void addDocument(@RequestBody DocumentDto document) {
    	documentService.ajouterDocument(document);
    }
}
