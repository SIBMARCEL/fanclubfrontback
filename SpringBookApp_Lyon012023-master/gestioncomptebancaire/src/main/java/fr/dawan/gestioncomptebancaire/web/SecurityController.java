package fr.dawan.gestioncomptebancaire.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	/*
	 * L'action slash (/) est l'action défaut 
	 * qui fera une redirection vers /bank/operations
	 */
	@GetMapping("/")
	public String home() {
		return "redirect:/bank/operations";
	}
	
	
	/*
	 * Une action qui indique à l'utilisateur qu'il n'a pas le droit d'acceder aux ressouces 
	 */
	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}

}
