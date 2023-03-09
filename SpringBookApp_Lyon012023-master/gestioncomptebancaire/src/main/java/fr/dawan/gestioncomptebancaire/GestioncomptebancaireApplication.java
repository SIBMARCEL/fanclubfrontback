package fr.dawan.gestioncomptebancaire;


import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import fr.dawan.gestioncomptebancaire.dao.ClientRepository;
import fr.dawan.gestioncomptebancaire.dao.CompteRepository;
import fr.dawan.gestioncomptebancaire.dao.OperationRepository;
import fr.dawan.gestioncomptebancaire.entities.Client;
import fr.dawan.gestioncomptebancaire.entities.Compte;
import fr.dawan.gestioncomptebancaire.entities.CompteCourant;
import fr.dawan.gestioncomptebancaire.entities.CompteEpargne;
import fr.dawan.gestioncomptebancaire.entities.Retrait;
import fr.dawan.gestioncomptebancaire.entities.Versement;

@SpringBootApplication
public class GestioncomptebancaireApplication implements CommandLineRunner{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private OperationRepository operationRepository;
	
	

	public static void main(String[] args) {
		SpringApplication.run(GestioncomptebancaireApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
//		Client c1 = clientRepository.save(new Client("Mathéo", "matheo@gmail.com"));
//		Client c2 = clientRepository.save(new Client("Louis", "louis@gmail.com"));
//
//		Compte cb1 = compteRepository.save(new CompteCourant("c1", LocalDate.now(), 300000, c1, 2000));
//		Compte cb2 = compteRepository.save(new CompteEpargne("c2", LocalDate.now(), 7000, c2, 5.5));
//
//		operationRepository.save(new Versement(LocalDate.now(), 72000, cb1));
//		operationRepository.save(new Versement(LocalDate.now(), 28000, cb1));
//		operationRepository.save(new Retrait(LocalDate.now(), 30000, cb1));
//		
//		operationRepository.save(new Versement(LocalDate.now(), 1500, cb2));
//		operationRepository.save(new Retrait(LocalDate.now(), 1000, cb2));

		
	}
	
	

}
