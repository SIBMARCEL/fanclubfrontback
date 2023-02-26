package fr.dawan.DemoMVC.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dawan.DemoMVC.FormBeans.ProduitForm;
import fr.dawan.DemoMVC.entities.Produit;
import fr.dawan.DemoMVC.services.IProduitService;

@Controller
@RequestMapping("produits")
public class ProduitController {
	
	@Autowired
	private IProduitService produitService;
	
	@GetMapping("/display")
	public String display(Model model) {
		List<Produit> prods = produitService.GetAll();
		model.addAttribute("produits", prods);
		return "produits";
	}
	
	@PostMapping("/addProduit")
	public String addProduit(@Valid @ModelAttribute("produitForm") ProduitForm pForm, BindingResult bindingResult, Model model ) {
		
		//bindingResult: objet qui contient les champs non valides + erreurs
		if(bindingResult.hasErrors()) {
			model.addAttribute("produits", produitService.GetAll());
			System.out.println(">>>>>>>>>>>> Formulaire non valide <<<<<<<<<<<<<<<");
			return "produits";
		}
		
		//Convertir produitForm en Produit
		//2 options possibles: ModelMapper 
		//- BeanUtils org.springframework.beans ---- les 2 objets doivent avoir les mm propriétés avec le mm nom	
		
		
		Produit p = new Produit();
		BeanUtils.copyProperties(pForm, p);
		
		if(p.getId() == 0) {
			produitService.Create(p);
		}else {
			produitService.Update(p);
		}
		
		return "redirect:/produits/display";
		
	}
	
	@GetMapping("/modifier/{id}")
	public String modifierProduit(@PathVariable("id") long id, Model model) {
		
		Produit prodTrouve = produitService.GetById(id);
		ProduitForm pForm = new ProduitForm();
		
		BeanUtils.copyProperties(prodTrouve, pForm);
		model.addAttribute("produitForm", pForm);
		model.addAttribute("produits", produitService.GetAll());
		return "produits";
	}
	
	@GetMapping("/delete/{id}")
	public String Delete(@PathVariable("id") long id, Model model) {
		Produit p = produitService.GetById(id);
		produitService.Delete(p);
		return "redirect:/produits/display";
	}
	
	@GetMapping("/findByKey")
	public String FindByKey(@RequestParam("motCle") String key, Model model) {
		List<Produit> lst = produitService.findByKey(key);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+lst.size());
		model.addAttribute("produits", lst);
		return "produits";
	}
	
	
	//Crée un objet ProduitForm et l'insère dans le model
	/*
	 * model.addAttribute("produitForm", new ProduitForm())
	 * 
	 */
	@ModelAttribute("produitForm")
	public ProduitForm getProduitForm() {
		return new ProduitForm();
	}

}
