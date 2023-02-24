package fr.dawan.gestionfanclub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.gestionfanclub.entities.User;

@Repository


public interface UserRepository extends JpaRepository<User, Long> {

	@Query("From User u WHERE u.email= :email")
	User findByEmail(@Param("email")String email);
	
	
}