package fr.dawan.gestioncomptebancaire.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.gestioncomptebancaire.dao.CompteRepository;
import fr.dawan.gestioncomptebancaire.dao.OperationRepository;
import fr.dawan.gestioncomptebancaire.entities.Compte;
import fr.dawan.gestioncomptebancaire.entities.CompteCourant;
import fr.dawan.gestioncomptebancaire.entities.Operation;
import fr.dawan.gestioncomptebancaire.entities.Retrait;
import fr.dawan.gestioncomptebancaire.entities.Versement;
import fr.dawan.gestioncomptebancaire.service.exception_.CompteIntrouvableException;
import fr.dawan.gestioncomptebancaire.service.exception_.SoldeInsuffisantException;
import fr.dawan.gestioncomptebancaire.service.exception_.VirementMemeCompteException;

@Service
@Transactional
public class BankServiceImpl implements IBankService {

	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private OperationRepository operationRepository;
	
	
	@Override
	public Compte consulterCompte(String numCompte) throws CompteIntrouvableException {
		
		Compte compte = compteRepository.findById(numCompte).orElse(null);
		
		if(compte == null) {
			throw new CompteIntrouvableException("Compte introuvable");
		}
		return compte;
	}

	
	@Override
	public void retirer(String numCompte, double montant) throws SoldeInsuffisantException, CompteIntrouvableException  {
		Compte cb = this.consulterCompte(numCompte);
		double decouvert = 0; //le compte courant
		
		if(cb instanceof CompteCourant) {  //Si c'est un compte courant
			decouvert = ((CompteCourant) cb).getDecouvert();
			//montant = 400   ....  sur mon compte j'ai 100  et un decouvert de 200
			//montant < 100 + 200 ==> retirer 
			//montant > 100 + 200 ==> solde insuffisant
			if(cb.getSolde() + decouvert < montant) {
				throw new SoldeInsuffisantException("Solde insuffisant");
			}
			
			//On crée l'operation de Retrait
			Retrait retrait = new Retrait(LocalDate.now(), montant, cb);
			
			//On enregistre l'operation de Retrait 
			operationRepository.save(retrait);
			
			//montant = 400
			//on retire = 200
			//solde = 200
			//On à jour le solde du compte
			cb.setSolde(cb.getSolde() - montant);
			
			compteRepository.save(cb);  //TO-DO si save ou update
	
		}
		
	}

	
	@Override
	public void verser(String numCompte, double montant) throws CompteIntrouvableException {
		Compte cb = this.consulterCompte(numCompte);
		Versement versement = new Versement(LocalDate.now(), montant, cb);
		operationRepository.save(versement);
		//On met à jour le solde 
		cb.setSolde(cb.getSolde() + montant);
		compteRepository.save(cb);
		
		
	}

	@Override
	public void virement(String numCompte1, String numCompte2, double montant) throws VirementMemeCompteException, SoldeInsuffisantException, CompteIntrouvableException {
		
		//Pas de virement dans le même compte 
		//Créer une exception métier (personalisée) : VirementMemeCompteException
		//Message de l'exception : "Impossible d'effectuer un virement sur le même compte"
		if(numCompte1.equals(numCompte2)) {
			throw new VirementMemeCompteException("Impossible d'effectuer un virement sur le même compte");
		}
		
		this.retirer(numCompte1, montant);
		this.verser(numCompte2, montant);

	}
	
	/*
	 * La pagination est très importante pour les performances d'une application
	 * Car elle economise de la mémoire en lisant es données en petits morceaux (page par page)
	 */
	@Override
	public Page<Operation> listOperation(String numCompte, int page, int size) {
		//PageRequest est une implementation Pageable
		return operationRepository.listOperation(numCompte, PageRequest.of(page, size));
	}

}
