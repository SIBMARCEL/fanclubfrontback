package fr.dawan.gestioncomptebancaire.service.exception_;

public class VirementMemeCompteException extends Exception {

	private String virementMemeCompteImposssible;

	public VirementMemeCompteException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VirementMemeCompteException(String message) {
		this.virementMemeCompteImposssible = message;
		System.out.println(virementMemeCompteImposssible);

	}

	public String getVirementMemeCompteImposssible() {
		return virementMemeCompteImposssible;
	}
	
	
	
}
