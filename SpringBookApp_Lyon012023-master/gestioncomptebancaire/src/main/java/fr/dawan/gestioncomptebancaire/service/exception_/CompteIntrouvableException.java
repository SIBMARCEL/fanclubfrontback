package fr.dawan.gestioncomptebancaire.service.exception_;


public class CompteIntrouvableException extends Exception {
	
	
	private String compteIntrouvable;

	public CompteIntrouvableException() {
		super();
	}

	
	public CompteIntrouvableException(String message) {
		this.compteIntrouvable = message;
		System.out.println(this.compteIntrouvable);
		
	}

	public String getCompteIntrouvable() {
		return compteIntrouvable;
	}

	
	
}
