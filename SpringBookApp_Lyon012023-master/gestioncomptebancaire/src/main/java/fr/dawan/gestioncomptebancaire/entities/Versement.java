package fr.dawan.gestioncomptebancaire.entities;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {

	private static final long serialVersionUID = -1350391279652164477L;

	public Versement() {
		super();
	}

	public Versement(LocalDate dateOperation, double montant, Compte compte) {
		super(dateOperation, montant, compte);
	}
	
	

}
