package fr.dawan.gestionfanclub.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.gestionfanclub.entities.Post;
import fr.dawan.gestionfanclub.service.PostImpl;

@RestController

@RequestMapping("/api/posts")
public class PostController {

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
	private PostImpl postService;
	
	//GET: /api/users --- liste des users
	// Données JSON dans le body de la réponse - status ok (200)
	
	@GetMapping(produces="application/json")
	public List<Post> GetAll(){
		return postService.GetAll();
	}
	
	//GET: /api/users/{id}
	// Données JSOn dans le body de la réponse - status ok (200)
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public Post GetById(@PathVariable("id") long id) {
		
		return postService.GetById(id);
	}
	
	//POST: /api/users/save
	//Convention: Post renvoie le status created (201) + ressource crée dans le body de la réponse
	
	
	@PostMapping(value="/save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Post> AddPost(@RequestParam("post") String postJson) throws JsonMappingException, JsonProcessingException{
		
		
		
		
		
		Post post = objectMapper.readValue(postJson, Post.class);
		
		Post resultat = postService.SaveOrUpdate(post);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resultat);
	}
	
	//DELETE: /api/users/delete/{id}
	
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<String> Delete(@PathVariable("id") Long id){
		
		Post post = postService.GetById(id);
		if(post != null) {
			
			
			postService.Delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("User with id = "+id+" deleted.");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		}
		
	}
	
		
}
