package fr.dawan.gestionfanclub.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.dawan.gestionfanclub.entities.User;


@Service
public interface IServiceUser {

	List<User> findAllAdmin(Pageable pageable);
	List<User> findAllUser(Pageable pageable);
	List<User> findAllSubscriber(Pageable pageable);
	User findAll();
	User findUserByid(long id);
	User findUserByPseudo(String pseudo);
	User findUserByEmail(String email);
	List<User>findAllActive(boolean active,Pageable pageable);
	void deleteUser(long id);
	User createUser(User user);
	User updateUser(User user);
	
}