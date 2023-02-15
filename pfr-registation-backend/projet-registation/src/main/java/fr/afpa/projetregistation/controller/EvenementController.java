package fr.afpa.projetregistation.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import fr.afpa.projetregistation.dto.EvenementDto;
import fr.afpa.projetregistation.dto.UtilisateurDto;
import fr.afpa.projetregistation.dto.UtilisateurSimpleDto;
import fr.afpa.projetregistation.service.IEvenementService;
import fr.afpa.projetregistation.service.IUtilisateurService;
import fr.afpa.projetregistation.utils.Calendrier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EvenementController {
	
	@Autowired
	IUtilisateurService userv;
	
	@Autowired
	IEvenementService eserv;
	
	@GetMapping(value="/evenement/{user}/{year}/{month}/{action}")
	protected ResponseEntity<String> fullCalendar(@PathVariable(value="year") int annee, 
								@PathVariable(value="month") int mois, 
								@PathVariable(value="action") char action,
								@PathVariable(value="user") String user) {
		String message = "Accès à la methode fullCalendar via une requête de type GET pour avoir la liste complète des jours d'un mois d'une année";
		
		log.info(message);
		YearMonth ym = null;
		if(action=='p') {
			ym = Calendrier.getPrevYearMonth(YearMonth.of(annee,mois));
		}else if(action=='s') {
			ym = Calendrier.getNextYearMonth(YearMonth.of(annee,mois));
		}else if(action=='n'){
			ym = YearMonth.of(annee,mois);
		}else {
			return null;
		}
		
		List<String> cal = Calendrier.getFullMonthOfStr(ym.getYear(), ym.getMonthValue());
		SimpleDateFormat dtf = new SimpleDateFormat("EEEE-dd-MM-yyyy",Locale.FRENCH);
		Date deb = null;
		Date fin = null;
		
		try {
			deb = dtf.parse(cal.get(0));
			fin = dtf.parse(cal.get(cal.size()-1));
			Calendar c = Calendar.getInstance();
			c.setTime(fin);
			c.add(Calendar.DATE, 1);
			fin = c.getTime();
			
			log.debug(deb.toString());
			log.debug(fin.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		List<EvenementDto> listEvent = eserv.getByDate(deb, fin,user);
		
		Gson gson = new Gson();
		Map<String,Object> res2 = new HashMap<>();
		res2.put("calendrier",cal);
		res2.put("titre",Calendrier.localizeMonth(ym.getMonthValue()));
		res2.put("event", listEvent);
		res2.put("current", ym);
		res2.put("success", 1);
		
		if(listEvent!=null) {
			log.debug(listEvent.toString());
		}else {
			log.debug("Liste NULL");
		}
		return ResponseEntity.ok(gson.toJson(res2));
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/evenement")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	protected ResponseEntity<EvenementDto> creeEvenement(@RequestBody EvenementDto event){
		String message = "Création d'un évènement sur la base des données envoyés par le front";
		log.info(message);
		
		Long s = (event.getDate_fin().getTime()-event.getDate_debut().getTime())/1000;
		int seconds = s.intValue();
		event.setDuree(seconds);
		event = eserv.create(event);
		return ResponseEntity.ok(event);
	}
	
	@RequestMapping(method=RequestMethod.PATCH, value="/evenement")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	protected ResponseEntity<EvenementDto> updateEvenement(@RequestBody EvenementDto event){
		String message = "Mise à jour d'un évènement sur la base des données envoyés par le front";
		log.info(message);
		UtilisateurDto udto = userv.getUtilisateurByMatricule(event.getUser().getMatricule());
		UtilisateurSimpleDto usdto = UtilisateurSimpleDto.builder()
										.dateDeNaissance(udto.getDateDeNaissance())
										.mail(udto.getMail())
										.matricule(udto.getMatricule())
										.nom(udto.getNom())
										.prenom(udto.getPrenom())
										.responsable(udto.isResponsable())
										.salaire(udto.getSalaire())
										.build();
		event.setUser(usdto);
		eserv.update(event);
		return null;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/evenement/{id}")
	protected ResponseEntity<EvenementDto> deleteEvenement(@PathVariable(value="id") int id){
		String message = "Suppression d'un évènement sur la base des données envoyés par le front";
		log.info(message);
		EvenementDto edto = eserv.getById(id);
		eserv.delete(edto);
		return null;
	}
}
