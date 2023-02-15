package fr.afpa.projetregistation.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins="http://localhost:4200")
@Controller
public class FileUploader {

	List<String> files = new ArrayList<String>();
//	private final Path rootLocation = Paths.get("_Path_To_Save_The_File");
	private final Path rootLocation = Paths.get("/projet-registation/documents-sauvegardes-registation");

@PostMapping("/sauvegarderdocument")
public ResponseEntity<String>
handleFileUpload(@RequestParam("file") MultipartFile file){
	String message;
	try {
		try {
			Files.copy(file.getInputStream(), this.rootLocation.resolve("Document-test-lambda-registation.pdf"));
		}catch (Exception e) {
			throw new RuntimeException("Echec de la sauvegarde du document.");
		}
		files.add(file.getOriginalFilename());

		message = "Document sauvegardé avec succès.";
				return ResponseEntity.status(HttpStatus.OK).body(message);
	}catch (Exception e) {
		message = "Une erreur est survenue durant la sauvegarde du document. Veuillez réessayer s'il vous plaît.";
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	}

}

}
