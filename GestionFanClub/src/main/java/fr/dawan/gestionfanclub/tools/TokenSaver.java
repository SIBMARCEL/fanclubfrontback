package fr.dawan.gestionfanclub.tools;

import java.util.HashMap;
import java.util.Map;

public class TokenSaver {
	
	//Cle: email
	//Valeur: token
	
	public static Map<String, String> tokenByEmail;
	static {
		tokenByEmail = new HashMap<>();
	}

}
