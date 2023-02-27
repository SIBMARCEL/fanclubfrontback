package fr.dawan.GestionFanClubFrontmvc.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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

import fr.dawan.GestionFanClubFrontmvc.entities.*;

import fr.dawan.GestionFanClubFrontmvc.FormBeans.*;

@Controller
public class UserController {
	
	//Utilise un Token
	
	final String BASE_URL = "http://localhost:8085";
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	private RestTemplate restTemplate; //Classe qui permet d'interagir avec des API REST
	
	
	private void getAllUsers(Model model, HttpSession session) {
		
		//Récupérer le token depuis la session
		LoginResponseDTO loginResponseDTO = (LoginResponseDTO) session.getAttribute("loginResponseDto");
		
		//Injection do token dans le header de la requ^te http
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization","Bearer "+loginResponseDTO.getToken());
		
		//Pour insérer le contenu du header dans la requête, on doit utilise un objet HttpEntity
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		
		ResponseEntity<User[]> reponse = restTemplate.exchange(BASE_URL+"/api/users",HttpMethod.GET, httpEntity, User[].class);
		List<User> users = Arrays.asList(reponse.getBody());
		
		
		//Récupérer les images des users
		for(User u : users) {
			
			//ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(BASE_URL+"/api/users/image/"+u.getId(), byte[].class); vesrion sans token
			ResponseEntity<byte[]> responseEntity = restTemplate.exchange(BASE_URL+"/api/users/image/"+u.getId(), HttpMethod.GET,httpEntity, byte[].class );
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
	

	
	
	
	@GetMapping("/users")
	public String AllUser(Model model, HttpSession session ) {
		
		/*
		 * restTemplate.exchange: permet d'insérer des infos dans le header de la requête. Ex: injection du token
		 * restTemplate.getForEntity: renvoie ResponseEntity: contient le status + données dans body
		 * restTemplate.getForObject: renvoie les données du body
		 * 
		 */
	
		getAllUsers(model, session);
		return "users";
	}
	
	
	@GetMapping("/user")
	public String user(Model model, HttpSession session ) {
		
		/*
		 * restTemplate.exchange: permet d'insérer des infos dans le header de la requête. Ex: injection du token
		 * restTemplate.getForEntity: renvoie ResponseEntity: contient le status + données dans body
		 * restTemplate.getForObject: renvoie les données du body
		 * 
		 */
		//
		
		return "update";
	}
	
	@GetMapping("/update")
	public String userupdate(Model model, HttpSession session ) {
		
		/*
		 * restTemplate.exchange: permet d'insérer des infos dans le header de la requête. Ex: injection du token
		 * restTemplate.getForEntity: renvoie ResponseEntity: contient le status + données dans body
		 * restTemplate.getForObject: renvoie les données du body
		 * 
		 */
		//
		
		return "update";
	}
//	
//	
//	
//	
//	
//	
//private void getcurrentUser(Model model, HttpSession session) {
//		
//		//Récupérer le token depuis la session
//		LoginResponseDTO loginResponseDTO = (LoginResponseDTO) session.getAttribute("loginResponseDto");
//		
//		//Injection do token dans le header de la requ^te http
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization","Bearer "+loginResponseDTO.getToken());
//		
//		//Pour insérer le contenu du header dans la requête, on doit utilise un objet HttpEntity
//		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
//		
//		ResponseEntity<User> reponse = restTemplate.exchange(BASE_URL+"/api//{id}",HttpMethod.GET, httpEntity, User.class);
//		
//		
//	User user = reponse.getBody();
//		
//		
//		//Récupérer les images des users
//		
//			
//			//ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(BASE_URL+"/api/users/image/"+u.getId(), byte[].class); vesrion sans token
//			ResponseEntity<byte[]> responseEntity = restTemplate.exchange(BASE_URL+"/api/users/image/"+user.getId(), HttpMethod.GET,httpEntity, byte[].class );
//			try {
//				
//				byte[] resource = responseEntity.getBody();
//				byte[] tab64 = Base64.getEncoder().encode(resource);
//				String stBas64 = new String(tab64,"utf-8");
//				user.setImageBase64(stBas64);			
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		
//		
//		model.addAttribute("users",user);
//	}
	
	@GetMapping(path = {"","/","home","accueil"})
	public String Accueil(HttpSession session) {
		
		return "index";
	}
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping(path = {"users/addOrUpdate","new"})
	public String addUpdate(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model model,
			@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
		
		

		if(userForm.getId() == 0 && file.getOriginalFilename().equals("")) {
			getAllUsers(model, session);
			model.addAttribute("Message", "Image obligatoire......");
			return "users";		
		}
		
		
		if(bindingResult.hasErrors()) {
			getAllUsers(model, session);
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
		
		if(session.getAttribute("loginResponseDto") == null) {
			return "redirect:/";
		}
		return "redirect:/users";
		
	}
	
	
	
	
	
	
	
	
	@PostMapping(path = {"users/addOrUpdateUser"})
	public String addUpdateUser(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model model,
			@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
		
		

		if(file.getOriginalFilename().equals("")) {
			
			model.addAttribute("Message", "Image obligatoire......");
			return "user";		
		}
//		
//		
		if(bindingResult.hasErrors()) {
			getAllUsers(model, session);
			return "user";
		}
//		
	
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
		if( !file.getOriginalFilename().equals("")) {
			params.add("file", new MultipartFileStream(file.getInputStream(), file.getOriginalFilename()));
		}
		
		//MAJ sans modification de l'image - champs file est vide
				if(file.getOriginalFilename().equals("")) {
					
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
		
		if(session.getAttribute("loginResponseDto") == null) {
			return "redirect:/";
		}
		
		
		session.invalidate();
		return "redirect:/";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("users/delete/{id}")
	public String DeleteUser(@PathVariable("id") int id, HttpSession session) {
		
		//System.out.println("voici l'id qu'on veut supprimer" +"  "+id);
		
		//restTemplate.delete(BASE_URL+"/api/users/delete/"+id);
		
		//Récupérer le token depuis la session
				LoginResponseDTO loginResponseDTO = (LoginResponseDTO) session.getAttribute("loginResponseDto");
				
				//Injection do token dans le header de la requ^te http
				HttpHeaders headers = new HttpHeaders();
				headers.add("Authorization","Bearer "+loginResponseDTO.getToken());
				
				//Pour insérer le contenu du header dans la requête, on doit utilise un objet HttpEntity
				HttpEntity<String> httpEntity = new HttpEntity<>(headers);
				
				ResponseEntity<String> reponse = restTemplate.exchange(BASE_URL+"/api/users/delete/"+id,HttpMethod.DELETE, httpEntity, String.class);
		
		return "redirect:/users";
	}
	
	
	
	@GetMapping("users/delete1/{id}")
	public String DeleteUser1(@PathVariable("id") int id, HttpSession session) {
		
		//System.out.println("voici l'id qu'on veut supprimer" +"  "+id);
		
		//restTemplate.delete(BASE_URL+"/api/users/delete/"+id);
		
		//Récupérer le token depuis la session
				LoginResponseDTO loginResponseDTO = (LoginResponseDTO) session.getAttribute("loginResponseDto");
				
				//Injection do token dans le header de la requ^te http
				HttpHeaders headers = new HttpHeaders();
				headers.add("Authorization","Bearer "+loginResponseDTO.getToken());
				
				//Pour insérer le contenu du header dans la requête, on doit utilise un objet HttpEntity
				HttpEntity<String> httpEntity = new HttpEntity<>(headers);
				
				ResponseEntity<String> reponse = restTemplate.exchange(BASE_URL+"/api/users/delete/"+id,HttpMethod.DELETE, httpEntity, String.class);
		
		
				
				
				
				
				return "redirect:/index";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("users/deleteu/{id}")
	public String DeleteUseru(@PathVariable("id") int id, HttpSession session) {
		
		//System.out.println("voici l'id qu'on veut supprimer" +"  "+id);
		
		//restTemplate.delete(BASE_URL+"/api/users/delete/"+id);
		
		//Récupérer le token depuis la session
				LoginResponseDTO loginResponseDTO = (LoginResponseDTO) session.getAttribute("loginResponseDto");
				
				//Injection do token dans le header de la requ^te http
				HttpHeaders headers = new HttpHeaders();
				headers.add("Authorization","Bearer "+loginResponseDTO.getToken());
				
				//Pour insérer le contenu du header dans la requête, on doit utilise un objet HttpEntity
				HttpEntity<String> httpEntity = new HttpEntity<>(headers);
				
				ResponseEntity<String> reponse = restTemplate.exchange(BASE_URL+"/api/users/delete/"+id,HttpMethod.DELETE, httpEntity, String.class);
		
				session.invalidate();
				return "redirect:/";
				
		
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("users/update/{id}")
	public String Update(@PathVariable("id") int id, Model model, HttpSession session) {
		
//	System.out.println("voici l'id qu'on veut modifier" +"  "+id);
		
		
		//User user = restTemplate.getForObject(BASE_URL+"/api/users/"+id, User.class);
		
		//byte[] resource = restTemplate.getForObject(BASE_URL+"/api/users/image/"+user.getId(), byte[].class);
		
		//Récupérer le token depuis la session
		LoginResponseDTO loginResponseDTO = (LoginResponseDTO) session.getAttribute("loginResponseDto");
		
		//Injection do token dans le header de la requ^te http
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization","Bearer "+loginResponseDTO.getToken());
		
		//Pour insérer le contenu du header dans la requête, on doit utilise un objet HttpEntity
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		
		ResponseEntity<User> rep1 = restTemplate.exchange(BASE_URL+"/api/users/"+id,HttpMethod.GET, httpEntity, User.class);
		User user = rep1.getBody();
		
		ResponseEntity<byte[]> rep2 = restTemplate.exchange(BASE_URL+"/api/users/image/"+user.getId(), HttpMethod.GET, httpEntity, byte[].class);
		byte[] resource = rep2.getBody();
		
		
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
		
		getAllUsers(model, session);
		model.addAttribute("userForm", userForm);
		
		return "users";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("users/updateuser/{id}")
	public String Updateuser(@PathVariable("id") int id, Model model, HttpSession session) {
		
//	System.out.println("voici l'id qu'on veut modifier" +"  "+id);
		
		
		//User user = restTemplate.getForObject(BASE_URL+"/api/users/"+id, User.class);
		
		//byte[] resource = restTemplate.getForObject(BASE_URL+"/api/users/image/"+user.getId(), byte[].class);
		
		//Récupérer le token depuis la session
		LoginResponseDTO loginResponseDTO = (LoginResponseDTO) session.getAttribute("loginResponseDto");
		
		//Injection do token dans le header de la requ^te http
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization","Bearer "+loginResponseDTO.getToken());
		
		//Pour insérer le contenu du header dans la requête, on doit utilise un objet HttpEntity
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		
		ResponseEntity<User> rep1 = restTemplate.exchange(BASE_URL+"/api/users/"+id,HttpMethod.GET, httpEntity, User.class);
		User user = rep1.getBody();
		
		ResponseEntity<byte[]> rep2 = restTemplate.exchange(BASE_URL+"/api/users/image/"+user.getId(), HttpMethod.GET, httpEntity, byte[].class);
		byte[] resource = rep2.getBody();
		
		
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
		
		//getAllUsers(model, session);
		model.addAttribute("userForm", userForm);
		
		return "user";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@GetMapping("users/update/{id}")
//	public String Update1(@PathVariable("id") int id, Model model, HttpSession session) {
//		
////	System.out.println("voici l'id qu'on veut modifier" +"  "+id);
//		
//		
//		//User user = restTemplate.getForObject(BASE_URL+"/api/users/"+id, User.class);
//		
//		//byte[] resource = restTemplate.getForObject(BASE_URL+"/api/users/image/"+user.getId(), byte[].class);
//		
//		//Récupérer le token depuis la session
//		LoginResponseDTO loginResponseDTO = (LoginResponseDTO) session.getAttribute("loginResponseDto");
//		
//		//Injection do token dans le header de la requ^te http
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization","Bearer "+loginResponseDTO.getToken());
//		
//		//Pour insérer le contenu du header dans la requête, on doit utilise un objet HttpEntity
//		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
//		
//		ResponseEntity<User> rep1 = restTemplate.exchange(BASE_URL+"/api/users/"+id,HttpMethod.GET, httpEntity, User.class);
//		User user = rep1.getBody();
//		
//		ResponseEntity<byte[]> rep2 = restTemplate.exchange(BASE_URL+"/api/users/image/"+user.getId(), HttpMethod.GET, httpEntity, byte[].class);
//		byte[] resource = rep2.getBody();
//		
//		
//		try {
//			
//			byte[] tab64 = Base64.getEncoder().encode(resource);
//			String stBas64 = new String(tab64,"utf-8");
//			user.setImageBase64(stBas64);
//			
//			//Sauvegarde de l'image en session
//			session.setAttribute("image", resource);
//			session.setAttribute("imagePath", user.getImagePath());
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		UserForm userForm = new UserForm();
//		BeanUtils.copyProperties(user, userForm);
//		
//		getAllUsers(model, session);
//		model.addAttribute("userForm", userForm);
//		
//		return "index";
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/connexion")
	public String Connect(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		
		LoginDTO loginDto = new LoginDTO();
		loginDto.setEmail(email);
		loginDto.setPassword(password);
		try {
			
			ResponseEntity<LoginResponseDTO> response = restTemplate.postForEntity(BASE_URL+"/login", loginDto, LoginResponseDTO.class);
			
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				//connexion OK
				
				LoginResponseDTO loginResponseDTO = response.getBody();
				session.setAttribute("loginResponseDto", loginResponseDTO);
				session.setAttribute("connect", true);
				session.setAttribute("admin", loginResponseDTO.isAdmin());
				session.setAttribute("sup", loginResponseDTO.getId());
				
				session.setAttribute("nom", loginResponseDTO.getFirstName());
				session.setAttribute("prenom", loginResponseDTO.getLastName());
				session.setAttribute("email", loginResponseDTO.getEmail());
				
//				
//	 System.out.println(session.getAttribute("sup"));
				
				//getAllUsers(model, session);
				
				//return "redirect:/users";
				
				return "redirect:/";
				
			}else {
				
				model.addAttribute("Error", "Echec connexion.........");
				return "index";
			}
			
		} catch (Exception e) {
			
			model.addAttribute("Error", "Echec connexion.........");
			return "index";
		}
		
	}
	
	
//	@GetMapping("/userid")
//	public String User(Model model, HttpSession session ) {
//		
//		/*
//		 * restTemplate.exchange: permet d'insérer des infos dans le header de la requête. Ex: injection du token
//		 * restTemplate.getForEntity: renvoie ResponseEntity: contient le status + données dans body
//		 * restTemplate.getForObject: renvoie les données du body
//		 * 
//		 */
//	
//		getAllUsers(model, session);
//		return "index";
//	}
//	
	
	
	
	
	
	
	
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/basket")
	public String basket() {
		return "basket.html";
	}
	
	
	@GetMapping("/foot")
	public String foot() {
		return "football.html";
	}
	
	@GetMapping("/volley")
	public String volley() {
		return "volley.html";
	}
	
	@GetMapping("/rugby")
	public String rugby() {
		return "rugby.html";
	}
	
	
	
	
	@GetMapping("/create-account")
	public String createAccount() {
		return "create-account";
	}
	
	@GetMapping("/logout")
	public String deconnexion(HttpSession session) {
		session.invalidate();
		return "redirect:/";
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