package fr.dawan.gestionfanclub.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.gestionfanclub.entities.Post;
import fr.dawan.gestionfanclub.repositories.PostRepository;
import fr.dawan.gestionfanclub.tools.JwtTokenTool;

@Service
public class PostImpl{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	JwtTokenTool jwtTokenTool;
	
	public List<Post> GetAll(){
		return postRepository.findAll();
	}
	
	public Post GetById(Long id) {
		Optional<Post> optional = postRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public Post SaveOrUpdate(Post post) {
		
//		
//		if(u.getId()==0) {;
//		
//		u.setDateInscription(LocalDate.now());
//		}
//		else {
//			u.setDateInscription(u.getDateInscription());
//		}
		post.setDatePublication(LocalDate.now());
//		u.setPassword(HashTool.cryptPassword(u.getPassword()));
		return postRepository.saveAndFlush(post);
		
		
		
		
		
	}
	
//	public Post FindPost(User user) {
//		Post post = postRepository.f
//		if(u != null) {
//			return u;
//		}
//		return null;
//	}
	
	public void Delete(Long id) {
		postRepository.deleteById(id);
	}

//	public LoginResponseDTO CheckLogin(LoginDTO loginDto) throws Exception {
//		
////		System.out.println(loginDto.getEmail());
//		
//		Post post = postRepository.findByEmail(loginDto.getEmail());
//		if(post != null && user.getPassword().equals(HashTool.cryptPassword(loginDto.getPassword()))) {	
//			
////			ModelMapper mapper = new ModelMapper();
////			mapper.map(user,  LoginResponseDTO.class);
//			
//			//Convertir user (entity) en loginResponseDto (DTO) en utilisant ModelMapper
//			LoginResponseDTO loginResponseDTO  = DtoTools.Convert(user, LoginResponseDTO.class);
//			
//			//Gestion du token
//			
//			//- choisir les infos du user a sauvegarder
////			Map<String, Object> claims = new HashMap<>();
////			claims.put("userId", user.getId());
////			claims.put("fullname", user.getFirstName()+" "+user.getLastName());
////			
////			//Générer le token
////			String token = jwtTokenTool.doGenerateToken(claims, user.getEmail());
////			
////			//Sauvegarde du token dans la map qu'utilise l'API pout vérifier le token
////			TokenSaver.tokenByEmail.put(user.getEmail(), token);
////			
////			//Injection du token loginResponseDTO
////			loginResponseDTO.setToken(token);
//			
//			
//			return loginResponseDTO;
//		}else {
//			throw new Exception("Echec connexion.");
//		}
//		
//		
//	}

}