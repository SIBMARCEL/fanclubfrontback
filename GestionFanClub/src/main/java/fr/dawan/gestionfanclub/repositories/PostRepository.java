package fr.dawan.gestionfanclub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.gestionfanclub.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	
	

}
