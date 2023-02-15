package fr.afpa.projetregistation.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Securite {

	public static String hashMD5(String password) {
			MessageDigest md;
			String myHash = null;
		try {
			md = MessageDigest.getInstance("MD5");
			 md.update(password.getBytes());
			 byte[] digest = md.digest();
			 myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myHash;
	}
}
