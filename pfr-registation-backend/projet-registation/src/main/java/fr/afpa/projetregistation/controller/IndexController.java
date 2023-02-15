package fr.afpa.projetregistation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.afpa.projetregistation.dto.DocumentDto;
import fr.afpa.projetregistation.utils.Constantes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	
	@RequestMapping(value = "/index" , method = {RequestMethod.POST, RequestMethod.GET })
	protected ModelAndView pageIndex() {
		ModelAndView mv = new ModelAndView();
		log.debug("Acces à l'index");

		mv.setViewName("index");

		return mv;
	}
}
