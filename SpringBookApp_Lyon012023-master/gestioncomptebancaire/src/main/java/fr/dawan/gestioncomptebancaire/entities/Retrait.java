package fr.dawan.gestioncomptebancaire.entities;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {

	private static final long serialVersionUID = 7663973359436803113L;

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(LocalDate dateOperation, double montant, Compte compte) {
		super(dateOperation, montant, compte);
		// TODO Auto-generated constructor stub
	}
	
	

}
