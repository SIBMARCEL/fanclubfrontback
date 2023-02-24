package fr.dawan.GestionFanClubFrontmvc.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.GestionFanClubFrontmvc.entities.User;
import fr.dawan.GestionFanClubFrontmvc.FormBeans.UserForm;

//@Controller
public class UserSansTokenController {
	
	final String BASE_URL = "http://localhost:8085";
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	private RestTemplate restTemplate; //Classe qui permet d'interagir avec des API REST
	
	
	private void getAllUsers(Model model) {
		User[] userTab = restTemplate.getForObject(BASE_URL+"/api/users", User[].class);
		List<User> users = Arrays.asList(userTab);
		
		//Récupérer les images des users
		for(User u : users) {
			ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(BASE_URL+"/api/users/image/"+u.getId(), byte[].class);
			try {
				
				byte[] resource = responseEntity.getBody();
				byte[] tab64 = Base64.getEncoder().encode(resource);
				String stBas64 = new String(tab64,"utf-8");
				u.setImageBase64(stBas64);			
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		model.addAttribute("users",users);
	}
	
	@GetMapping("")
	public String Accueil() {
		return "index";
	}
	
	@GetMapping("/users")
	public String AllUser(Model model) {
		
		/*
		 * restTemplate.exchange: permet d'insérer des infos dans le header de la requête. Ex: injection du token
		 * restTemplate.getForEntity: renvoie ResponseEntity: contient le status + données dans body
		 * restTemplate.getForObject: renvoie les données du body
		 * 
		 */
//		User[] userTab = restTemplate.getForObject(BASE_URL+"/api/users", User[].class);
//		List<User> users = Arrays.asList(userTab);
		
		//Récupérer les images des users
//		for(User u : users) {
//			ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(BASE_URL+"/api/users/image/"+u.getId(), byte[].class);
//			try {
//				
//				byte[] resource = responseEntity.getBody();
//				byte[] tab64 = Base64.getEncoder().encode(resource);
//				String stBas64 = new String(tab64,"utf-8");
//				u.setImageBase64(stBas64);			
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//		
//		model.addAttribute("users",users);
//		
		getAllUsers(model);
		return "users";
	}
	
	@PostMapping("users/addOrUpdate")
	public String addUpdate(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model model,
			@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
		
		

		if(userForm.getId() == 0 && file.getOriginalFilename().equals("")) {
			getAllUsers(model);
			model.addAttribute("Message", "Image obligatoire......");
			return "users";		
		}
		
		
		if(bindingResult.hasErrors()) {
			getAllUsers(model);
			return "users";
		}
		
		//Créer userJson: un user sous format json : {"firstName":"ddd", "lastName":"ddd".....}
		String userJson = objectMapper.writeValueAsString(userForm);
		
		//Création d'un RequestPart
		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		
		params.add("user", userJson);
		
		//Gestion du file
		//Création: image obligatoire
		if(userForm.getId() == 0) {
			params.add("file", new MultipartFileStream(file.getInputStream(), file.getOriginalFilename()));
		}
		
		//MAJ avec champs file est non vide
		if(userForm.getId() != 0 && !file.getOriginalFilename().equals("")) {
			params.add("file", new MultipartFileStream(file.getInputStream(), file.getOriginalFilename()));
		}
		
		//MAJ sans modification de l'image - champs file est vide
				if(userForm.getId() != 0 && file.getOriginalFilename().equals("")) {
					
					//Récupérer l'image depuis la session
					byte[] resource = (byte[]) session.getAttribute("image");
					String imagePath = (String) session.getAttribute("imagePath");
					params.add("file", new MultipartFileStream(new ByteArrayInputStream(resource), imagePath));
					
					session.removeAttribute("image");
					session.removeAttribute("imagePath");
					
				}
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = 
				new HttpEntity<LinkedMultiValueMap<String,Object>>(params, headers);
		
		ResponseEntity<User> postForEntity = restTemplate.postForEntity(BASE_URL+"/api/users/save", httpEntity, User.class);
		
		return "redirect:/users";
		
	}
	
	@GetMapping("users/delete/{id}")
	public String DeleteUser(@PathVariable("id") int id) {
		
		restTemplate.delete(BASE_URL+"/api/users/delete/"+id);
		
		return "redirect:/users";
	}
	
	@GetMapping("users/update/{id}")
	public String Update(@PathVariable("id") int id, Model model, HttpSession session) {
		
		User user = restTemplate.getForObject(BASE_URL+"/api/users/"+id, User.class);
		
		byte[] resource = restTemplate.getForObject(BASE_URL+"/api/users/image/"+user.getId(), byte[].class);
		
		try {
			
			byte[] tab64 = Base64.getEncoder().encode(resource);
			String stBas64 = new String(tab64,"utf-8");
			user.setImageBase64(stBas64);
			
			//Sauvegarde de l'image en session
			session.setAttribute("image", resource);
			session.setAttribute("imagePath", user.getImagePath());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		UserForm userForm = new UserForm();
		BeanUtils.copyProperties(user, userForm);
		
		getAllUsers(model);
		model.addAttribute("userForm", userForm);
		
		return "users";
	}
	
	
	@ModelAttribute("userForm")
	public UserForm getUserForm() {
		return new UserForm();
	}
	
	//Classe perso pour gérer le multipart file
	class MultipartFileStream extends InputStreamResource{

		private final String filename;
		
		public MultipartFileStream(InputStream inputStream, String filename) {
			super(inputStream);
			this.filename= filename;
		}
		
		

		@Override
		public String getFilename() {
			// TODO Auto-generated method stub
			return this.filename;
		}



		@Override
		public long contentLength() throws IOException {
			// TODO Auto-generated method stub
			return -1;
		}



		
	}

}
