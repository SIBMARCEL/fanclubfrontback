package fr.dawan.gestioncomptebancaire.entities;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

	private static final long serialVersionUID = -7701895841962415675L;

	private double decouvert;

	public CompteCourant() {
		// TODO Auto-generated constructor stub
	}

	public CompteCourant(String numCompte, LocalDate dateCreation, double solde, Client client, double decouvert) {
		super(numCompte, dateCreation, solde, client);
		this.decouvert = decouvert;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

}
