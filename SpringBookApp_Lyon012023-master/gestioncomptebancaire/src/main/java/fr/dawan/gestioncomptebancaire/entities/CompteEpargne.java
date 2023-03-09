package fr.dawan.gestioncomptebancaire.entities;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {

	private static final long serialVersionUID = -4878147168306604800L;
	private double taux; 
	
	public CompteEpargne() {
		// TODO Auto-generated constructor stub
	}

	public CompteEpargne(String numCompte, LocalDate dateCreation, double solde, Client client, double taux) {
		super(numCompte, dateCreation, solde, client);
		this.taux = taux;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	
	
}
