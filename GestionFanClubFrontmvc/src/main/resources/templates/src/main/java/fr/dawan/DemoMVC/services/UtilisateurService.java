package fr.dawan.DemoMVC.services;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.DemoMVC.entities.Utilisateur;
import fr.dawan.DemoMVC.repositories.UserRepository;
import fr.dawan.DemoMVC.tools.HashTool;

@Service
@Transactional
public class UtilisateurService implements IUtilisateurService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void Create(Utilisateur u) { //begin transaction
		//Crypter le password
		u.setPassword(HashTool.cryptPassword(u.getPassword()));
		userRepository.save(u);
	}
	
	@Override
	public void delete(Utilisateur u) {
		userRepository.delete(u);
	}
	
	@Override
	public List<Utilisateur> GetAll(){
		return userRepository.findAll();
	}
	
	@Override
	public Utilisateur GetById(long id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public Utilisateur FindByEmail(String email) {
		return userRepository.findByEmail(email);
				
	}
	
	@Override
	public void Update(Utilisateur u) { //u contient password fournit par le front
		
		//Vérifier si password est modifié
		Utilisateur userDB =  GetById(u.getId()); //userDB  contient password crypté
		
		if(!userDB.getPassword().equals(u.getPassword())) {
			u.setPassword(HashTool.cryptPassword(u.getPassword()));
		}
		
		userRepository.save(u);
		
		
	}

}
