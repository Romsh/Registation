package fr.afpa.projetregistation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ConstructionController {

	@GetMapping("/")
	protected ModelAndView accueil() {
		ModelAndView mv = new ModelAndView();
		log.info("Affichage de la page en construction");
		mv.setViewName("construction");
		return mv;
	}
}
