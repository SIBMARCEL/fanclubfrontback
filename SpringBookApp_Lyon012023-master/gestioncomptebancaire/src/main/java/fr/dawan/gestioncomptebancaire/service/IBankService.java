package fr.dawan.gestioncomptebancaire.service;

import org.springframework.data.domain.Page;

import fr.dawan.gestioncomptebancaire.entities.Compte;
import fr.dawan.gestioncomptebancaire.entities.Operation;
import fr.dawan.gestioncomptebancaire.service.exception_.CompteIntrouvableException;
import fr.dawan.gestioncomptebancaire.service.exception_.SoldeInsuffisantException;
import fr.dawan.gestioncomptebancaire.service.exception_.VirementMemeCompteException;

public interface IBankService {

	public Compte consulterCompte(String numCompte) throws CompteIntrouvableException;
	public void retirer(String numCompte, double montant) throws SoldeInsuffisantException, CompteIntrouvableException; 
	public void verser(String numCompte, double montant) throws CompteIntrouvableException;
	public void virement(String numCompte1, String numCompte2, double montant) throws VirementMemeCompteException, SoldeInsuffisantException, CompteIntrouvableException;
	public Page<Operation> listOperation(String numCompte, int page, int size);
	
	
}
