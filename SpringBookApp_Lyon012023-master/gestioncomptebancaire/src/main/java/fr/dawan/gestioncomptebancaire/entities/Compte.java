package fr.dawan.gestioncomptebancaire.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CPTE", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Compte implements Serializable{

	private static final long serialVersionUID = -5534190747827835313L;

	@Id
	private String numCompte;

	private LocalDate dateCreation;
	private double solde;

	//ManyToOne ==> Many: Compte  Client: One
	@ManyToOne
	@JoinColumn(name="CODE_CLI")
	private Client client;

	@OneToMany(mappedBy = "compte")
	private Collection<Operation> operations;

	public Compte() {
	}


	public Compte(String numCompte, LocalDate dateCreation, double solde, Client client) {
		super();
		this.numCompte = numCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.client = client;
	}


	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public LocalDate getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}


	public Collection<Operation> getOperations() {
		return operations;
	}


	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}



}
