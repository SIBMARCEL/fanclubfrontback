package fr.dawan.DemoMVC.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import fr.dawan.DemoMVC.entities.Utilisateur;

@Repository
public interface UserRepository extends JpaRepository<Utilisateur, Long>{
	
	//Les finder méthode de spring data
	
	Utilisateur findByEmail(String email);

}
