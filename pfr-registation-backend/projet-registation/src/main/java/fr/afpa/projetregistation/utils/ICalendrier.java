package fr.afpa.projetregistation.utils;

import org.springframework.util.StringUtils;

/**
 * Interface servant à tester la fonctionnalité des méthodes private dans une interfaces
 * Elle généralise une opération utilise dans le cadre du {@link Calendrier} afin 
 * de récupérer le nombre de jour avant un mois ou après un mois
 * afin de faire un carré parfait.
 * @author Samuel
 *
 */
public interface ICalendrier {
	
	/**
	 * 
	 * @param jour Il s'agit de la version texte du jour de la semaine
	 * @param action "n" si on veut les jours après le mois et "p" si on veut les jours précédents
	 * @return {link Integer} représentant le nombre de jour.
	 */
	private static int getSwitch(String jour, char action) {
		int res = 0;
		switch(jour) {
			case "Lundi" :
				if(action=='n') res = 6;
				break;
			case "Mardi" :
				if(action=='n') res = 5;
				else if(action=='p') res = 1;
				break;
			case "Mercredi" :
				if(action=='n') res = 4;
				else if(action=='p') res = 2;
				break;
			case "Jeudi" :
				res = 3;
				break;
			case "Vendredi" :
				if(action=='n') res = 2;
				else if(action=='p') res = 4;
				break;
			case "Samedi" :
				if(action=='n') res = 1;
				else if(action=='p') res = 5;
				break;
			default :
				res = 6;
				break;
		}
		return res;
	}
	
	/**
	 * Methode accessible à tous utilisant la méthode privé
	 * @param day Jour sous le format "Mardi-01-09-2020" (date non contractuel)
	 * @param action "n" pour les jours suivants le mois et "p" pour les jours précédents
	 * @return {link Integer} représentant le nombre de jour.
	 */
	public default int getNbJour(String day, char action) {
		String j = StringUtils.capitalize(day.split("\\-")[0]);
		int nbJour = 0;
		nbJour = getSwitch(j,action);
		return nbJour;
	}
}
