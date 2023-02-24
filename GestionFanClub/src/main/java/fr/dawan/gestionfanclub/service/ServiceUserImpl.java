package fr.dawan.gestionfanclub.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.gestionfanclub.dto.*;

import fr.dawan.gestionfanclub.tools.*;
import fr.dawan.gestionfanclub.entities.User;
import fr.dawan.gestionfanclub.repositories.UserRepository;

@Service
public class ServiceUserImpl{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	JwtTokenTool jwtTokenTool;
	
	public List<User> GetAll(){
		return userRepository.findAll();
	}
	
	public User GetById(Long id) {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public User SaveOrUpdate(User u) {
		return userRepository.saveAndFlush(u);
	}
	
	public User FindByEmail(String email) {
		User u = userRepository.findByEmail(email);
		if(u != null) {
			return u;
		}
		return null;
	}
	
	public void Delete(Long id) {
		userRepository.deleteById(null);
	}

	public LoginResponseDTO CheckLogin(LoginDTO loginDto) throws Exception {
		
		System.out.println(loginDto.getEmail());
		
		User user = userRepository.findByEmail(loginDto.getEmail());
		if(user != null && user.getPassword().equals(loginDto.getPassword())) {	
			
//			ModelMapper mapper = new ModelMapper();
//			mapper.map(user,  LoginResponseDTO.class);
			
			//Convertir user (entity) en loginResponseDto (DTO) en utilisant ModelMapper
			LoginResponseDTO loginResponseDTO  = DtoTools.Convert(user, LoginResponseDTO.class);
			
			//Gestion du token
			
			//- choisir les infos du user a sauvegarder
			Map<String, Object> claims = new HashMap<>();
			claims.put("userId", user.getId());
			claims.put("fullname", user.getNom()+" "+user.getPrenom());
			
			//Générer le token
			String token = jwtTokenTool.doGenerateToken(claims, user.getEmail());
			
			//Sauvegarde du token dans la map qu'utilise l'API pout vérifier le token
			TokenSaver.tokenByEmail.put(user.getEmail(), token);
			
			//Injection du token loginResponseDTO
			loginResponseDTO.setToken(token);
			
			
			return loginResponseDTO;
		}else {
			throw new Exception("Echec connexion.");
		}
		
		
	}

}