package fr.dawan.gestioncomptebancaire.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_OPERATION", discriminatorType = DiscriminatorType.STRING, length = 1)
public abstract class Operation implements Serializable{

	private static final long serialVersionUID = -6270397253888610730L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numOperation;
	
	private LocalDate dateOperation;
	private double montant;
	
	
	@ManyToOne
	@JoinColumn(name="NUM_CPTE")
	private Compte compte;
	
	public Operation() {
		// TODO Auto-generated constructor stub
	}


	public Operation(LocalDate dateOperation, double montant, Compte compte) {
		super();
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.compte = compte;
	}


	public long getNumOperation() {
		return numOperation;
	}


	public void setNumOperation(long numOperation) {
		this.numOperation = numOperation;
	}


	public LocalDate getDateOperation() {
		return dateOperation;
	}


	public void setDateOperation(LocalDate dateOperation) {
		this.dateOperation = dateOperation;
	}


	public double getMontant() {
		return montant;
	}


	public void setMontant(double montant) {
		this.montant = montant;
	}


	public Compte getCompte() {
		return compte;
	}


	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	

}
