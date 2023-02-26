package fr.dawan.DemoMVC.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.dawan.DemoMVC.entities.Utilisateur;
import fr.dawan.DemoMVC.services.IUtilisateurService;

@Controller
@RequestMapping("utilisateurs")
public class UtilisateurController {
	
	
	@Autowired
	private IUtilisateurService userService;
	
	@GetMapping("/display")
	public String display(Model model) {
		List<Utilisateur> users = userService.GetAll();
		/*
		 * Pour afficher une image (sauvegardée comme un tableau de bytes dans le base de données) dans une page html
		 * il faut la convertir en String base64 en utilisant la classe Base64 qui renvoie un byte[] contenant des String en base64
		 * qu'il faut convertir par la suite String tout court: new String(byte[], 'utf-8')
		 * 
		 * 
		 */
		//Conversion du byte[] en string base64
		for(Utilisateur u : users) {
			byte[] encondedBase64 = Base64.getEncoder().encode(u.getPhoto());
			String imageBase64;
			try {
				imageBase64 = new String(encondedBase64, "UTF-8");
				u.setBase64Image(imageBase64);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		model.addAttribute("users", users);
		
		return "utilisateurs";
	}
	/*
	 * Pour les formulaires contenant des fichiers(image, pdf....), dans la page html vous devez ajouter le paramètre enctype="multipart/form-data"
	 * et dans le controller pour récupérer le fichier transmis par le formulaire, vous devez ajouter dans l'action du controller
	 * le paramètre @RequestParam MultipartFile
	 * 
	 * 
	 */
	@PostMapping("/addUser")
	public String addUser(@RequestParam("email") String email, @RequestParam("password") String password, 
			@RequestParam("file") MultipartFile file, Model model) throws IOException {
		
		
		if(file.getOriginalFilename().equals("")) {
			model.addAttribute("Error", "Photo jpeg (jpg) obligatoire....");
			return "create-account";
		}
		Utilisateur user = new Utilisateur(email, password, false, file.getBytes());
		
		userService.Create(user);
		
		return "login";
	}

}
