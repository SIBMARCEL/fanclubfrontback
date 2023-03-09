package fr.dawan.springmvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.dawan.springmvc.beans.User;
import fr.dawan.springmvc.service.IUserService;


/*
 * Un controleur Spring MVC est une classe qui est annotée avec @Controller (Obligatoire)
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	/*
	 * @Autowired : 
	 */
	@Autowired
	private IUserService service; //service = null

	//	@RequestMapping(value = "/users")
	//	public String lstUser() {
	//		  
	//		return "users";
	//	}


	@GetMapping("/users")
	public ModelAndView lstUser() {
		ModelAndView modelView = new ModelAndView();
		List<User> users = service.findAll();
		modelView.setViewName("users");
		modelView.addObject("users", users);
		return modelView;
	}

	/**
	 * Admin pourra effacer un user par son id 
	 * @PathVariable : est une annotation Spring qui indique qu'un paramètre de la méthode doit être 
	 * lié à une variable de la requête (url)
	 * @param model
	 * @param id
	 * @return
	 */
//	@GetMapping("/users/delete/{id}")  //users/delete/1
//	public String deleteUser(Model model, @PathVariable long id) {
//		
//	}

}
