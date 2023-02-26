package fr.dawan.DemoMVC.controllers;

import java.util.Base64;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dawan.DemoMVC.entities.Utilisateur;
import fr.dawan.DemoMVC.services.IUtilisateurService;
import fr.dawan.DemoMVC.tools.HashTool;

@Controller
public class LoginController {
	
	@Autowired
	private IUtilisateurService userService;
	
	@GetMapping("/login")
	public String display() {
		//return "login";
		throw new RuntimeException();
	}
	
	@PostMapping("/authentification")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		
		Utilisateur user = userService.FindByEmail(email);
		if(user != null && user.getPassword().equals(HashTool.cryptPassword(password))) {
			//Connexion OK
			
			//Gérer l'affichage de la photo
			byte[] encondedBase64 = Base64.getEncoder().encode(user.getPhoto());
			String imageBase64;
			try {
				imageBase64 = new String(encondedBase64, "UTF-8");
				user.setBase64Image(imageBase64);
			}catch (Exception e){
				e.printStackTrace();
			}
			//Durée par defaut = 25 mn
			//Tant que l'application est en cours d'exécution, le contenu de la session est disponible
			
			session.setAttribute("user", user);
			session.setAttribute("email",user.getEmail());
			session.setAttribute("image", user.getBase64Image());
			if(user.isAdmin()) {
				session.setAttribute("admin", true);
			}else {
				session.setAttribute("admin", false);
			}
			
			return "redirect:/utilisateurs/display";
		}else{			
			model.addAttribute("errorMessage", "Echec connexion.........");
			return "login";
		}
		
		
	}
	
	@GetMapping("/create-account")
	public String createUser() {
		return "create-account";
	}
	
	@GetMapping("/logout")
	public String deconnexion(HttpSession session) {
		session.invalidate(); //Toute la session est supprimée
		//session.removeAttribute("image"); 
		return "login";
	}

}
