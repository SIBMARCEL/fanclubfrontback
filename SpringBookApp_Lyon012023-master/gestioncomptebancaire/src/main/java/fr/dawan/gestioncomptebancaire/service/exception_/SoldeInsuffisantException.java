package fr.dawan.gestioncomptebancaire.service.exception_;

public class SoldeInsuffisantException extends Exception {

	private String soldeInsuffisant;

	public SoldeInsuffisantException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SoldeInsuffisantException(String message) {
		this.soldeInsuffisant = message;
		System.out.println(this.soldeInsuffisant);
	}

	public String getSoldeInsuffisant() {
		return soldeInsuffisant;
	} 
	
	
	
}
