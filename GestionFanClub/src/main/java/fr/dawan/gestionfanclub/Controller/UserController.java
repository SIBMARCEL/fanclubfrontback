package fr.dawan.gestionfanclub.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.gestionfanclub.entities.User;
import fr.dawan.gestionfanclub.service.ServiceUserImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

	//Ajouter un dossier images à la racine du projet qui va contenir les photos de profil
	//System.getProperty("user.dir") renvoie le chemin vers le dossier racine du projet : c:\.....\DemoApiRest
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/images";
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/*
	 * Api Rest: un ensemble de ressources
	 * - Chaque ressource possède un id (URI - End Point)
	 * - Chaque ressource possède une méthode d'accès
	 * - Chaque ressource est soit publique, soit privée
	 * 
	 * Swagger ( appelé OpenApi en v3 ): est un outil qui  nous de décrire et de documenter les Api Rest
	 * Pour générer la doc de l'Api, ajouter le dépendece Maven spring doc openapi
	 * <dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.6.14</version>
		</dependency>
		1 -Ensuite démarrer l'api, et exécuter l'url: http://localhost:8085/v3/api-docs/
		2- Récupérer la doc JSON, la personnaliser dasn Swagger Editor - la partager avec les utilisateur de l'API
		
	 */

	@Autowired
	private ServiceUserImpl userService;
	
	//GET: /api/users --- liste des users
	// Données JSON dans le body de la réponse - status ok (200)
	
	@GetMapping(produces="application/json")
	public List<User> GetAll(){
		return userService.GetAll();
	}
	
	//GET: /api/users/{id}
	// Données JSOn dans le body de la réponse - status ok (200)
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public User GetById(@PathVariable("id") int id) {
		return userService.GetById(null);
	}
	
	//POST: /api/users/save
	//Convention: Post renvoie le status created (201) + ressource crée dans le body de la réponse
	
	
	@PostMapping(value="/save", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<User> AddUser(@RequestParam("user") String userJson, @RequestPart("file") MultipartFile file) throws Exception{
		
		//gestion du file
		File fichier = new File(uploadDirectory+"/"+file.getOriginalFilename());
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fichier));
		bos.write(file.getBytes());
		bos.close();
		
		//Gestion userJson { "firstName:"ff", lastName":"dsdd", email":"sds@dd", "password":"sdqsdqsd", imagePath:""}
		//Conversion Json vers Objet et vice versa - ObjectMapper
		
		User u = objectMapper.readValue(userJson, User.class);
		u.setImagePath(file.getOriginalFilename());
		User resultat = userService.SaveOrUpdate(u);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resultat);
	}
	
	//DELETE: /api/users/delete/{id}
	
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<String> Delete(@PathVariable("id") Long id){
		
		User user = userService.GetById(id);
		if(user != null) {
			userService.Delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("User with id = "+id+" deleted.");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		}
		
	}
	
	@GetMapping(value="/image/{id}")
	public ResponseEntity<Resource> GetUserImage(@PathVariable("id") Long id) throws IOException{
		User user = userService.GetById(id);
		Resource resource = null;
		try {
			Path path = Paths.get(uploadDirectory).resolve(user.getImagePath());
			resource = new UrlResource(path.toUri());
			
			if(!resource.exists()) {
				throw new RuntimeException();
			}
			
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		Path newPath = resource.getFile().toPath();
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(newPath)) //préciser le type du contenu (image, son, pdf...)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"") //insertion du lien de téléchargement
				.body(resource);
		
	}
}
