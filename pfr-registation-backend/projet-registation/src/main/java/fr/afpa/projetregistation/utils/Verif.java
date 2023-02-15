package fr.afpa.projetregistation.utils;

public class Verif {

	public static boolean verifPrixUnitaire(String pPrixUnitaire) {
		boolean res = false;
		try {
			double pu = Double.parseDouble(pPrixUnitaire);
			if (pu > 0) {
				res = true;
			} else {
				res = false;
			}
		} catch (Exception e) {
			res = false;
		}
		return res;
	}
	
	public static boolean verifAnneeParution(String pAnnee) {
		boolean res = false;
		try {
			int annee = Integer.parseInt(pAnnee);
			if (annee > 0 && annee < 2021) {
				res = true;
			} else {
				res = false;
			}
		} catch (Exception e) {
			res = false;
		}
		return res;
	}

	public static boolean verifQteStock(String pQteStock) {
		boolean res = false;
		try {
			int qte = Integer.parseInt(pQteStock);
			if (qte > 0) {
				res = true;
			} else {
				res = false;
			}
		} catch (Exception e) {
			res = false;
		}
		return res;
	}
	
}
