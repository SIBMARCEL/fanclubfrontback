package fr.dawan.gestioncomptebancaire.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dawan.gestioncomptebancaire.entities.Compte;
import fr.dawan.gestioncomptebancaire.entities.Operation;
import fr.dawan.gestioncomptebancaire.service.IBankService;
import fr.dawan.gestioncomptebancaire.service.exception_.CompteIntrouvableException;
import fr.dawan.gestioncomptebancaire.service.exception_.SoldeInsuffisantException;
import fr.dawan.gestioncomptebancaire.service.exception_.VirementMemeCompteException;

//Un controller c'est une classe qui contient l'annotation @Controller
@Controller
@RequestMapping("/bank")
public class BankController {

	@Autowired
	private IBankService bankService;

	@GetMapping("/operations")
	public String index() {
		return "comptes";
	}
	
	
	
	
	

	/*
	 * http://localhost:8081/bank/consultercompte?numCompte=c1&page=3&size=15
	 * RequestParam permet d'attribuer un nom au paramètre d'url qu'on veut recuperer 
	 * dans notre cas les paramètres s'appellent pafe et size 
	 */
	@GetMapping("/consultercompte")
	public String consulterCompte(Model model, String numCompte, 
			@RequestParam(name="page", defaultValue="0") int page, 
			@RequestParam(name="size", defaultValue="7") int size) {
		
		model.addAttribute("numCompte", numCompte);
		
		Compte cb;
		try {
			cb = bankService.consulterCompte(numCompte);
			model.addAttribute("compte", cb);
			
			Page<Operation> pageOperations = bankService.listOperation(numCompte, page, size);
			
			//La methode getContent permet de retourner la liste des operations
			model.addAttribute("listOperations", pageOperations.getContent());
			
			//Recupère le nombre de page 
			int[] pages = new int[pageOperations.getTotalPages()];
			model.addAttribute("pages", pages);
			
			//Transferer la taille  dans le modele 
			model.addAttribute("size", size);
			
			//Transferer la page courante dans le modele
			model.addAttribute("pageCourante", page);
			
		} catch (CompteIntrouvableException e) {

			e.printStackTrace();
			model.addAttribute("compteIntrouvableException", e.getCompteIntrouvable());
		}

		return "comptes";
	}

	

	@PostMapping("/saveOperation")
	public String saveOperation(Model model, String typeOperation, String numCompte, double montant, 
			String numCompte2) {

		try {
			if(typeOperation.equals("versement")) {
				bankService.verser(numCompte, montant);
			} else if(typeOperation.equals("retrait")){
				bankService.retirer(numCompte, montant);
			} else if(typeOperation.equals("virement")) {
				bankService.virement(numCompte, numCompte2, montant);
			}
		} catch (CompteIntrouvableException e) {
			e.printStackTrace();
			model.addAttribute("compteIntrouvableException", e.getCompteIntrouvable());
			return "redirect:/bank/consultercompte?numCompte="+numCompte + 
					"&compteIntrouvableException="+ e.getCompteIntrouvable();
		} catch (SoldeInsuffisantException e) {
			e.printStackTrace();
			model.addAttribute("soldeInsuffisantException", e.getSoldeInsuffisant());
			return "redirect:/bank/consultercompte?numCompte="+numCompte + 
					"&soldeInsuffisantException="+ e.getSoldeInsuffisant();
		
		} catch (VirementMemeCompteException e) {
			e.printStackTrace();
			model.addAttribute("virementMemeCompteException", e.getVirementMemeCompteImposssible());
			return "redirect:/bank/consultercompte?numCompte="+numCompte + 
					"&virementMemeCompteException="+ e.getVirementMemeCompteImposssible();
		}
		
		/*
		 * Il faut faire une redirection, une fois qu'on a sauvé le type d'operation 
		 * On va se rediriger vers la méthode consulterCompte en lui passant numCompte 
		 * (car c'est une operation qui concerne le compte)
		 * 
		 */
		return "redirect:/bank/consultercompte?numCompte="+numCompte;

	}

}
