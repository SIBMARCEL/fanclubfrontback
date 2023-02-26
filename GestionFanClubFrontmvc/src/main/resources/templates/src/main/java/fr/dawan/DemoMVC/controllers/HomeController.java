package fr.dawan.DemoMVC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.dawan.DemoMVC.entities.Utilisateur;
import fr.dawan.DemoMVC.services.IUtilisateurService;

@Controller
public class HomeController {
	
	@Autowired
	private IUtilisateurService userService;

	@GetMapping("/")
	public String Accueil(Model model) {
		model.addAttribute("message", "Page d'accueil");
		//throw new RuntimeException();
		return "index";
	}
	
	@GetMapping("/load")
	public String LoadTestData() {
		
		if(userService.GetAll().size() == 0) {
			userService.Create(new Utilisateur("admin@dawan.fr", "admin", true, "".getBytes()));
			userService.Create(new Utilisateur("user@dawan.fr", "user", false, "".getBytes()));
		}		
		
		return "index";
	}
}
