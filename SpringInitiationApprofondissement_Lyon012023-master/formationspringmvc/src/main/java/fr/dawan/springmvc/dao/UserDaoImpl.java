package fr.dawan.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.springmvc.beans.User;

@Repository
public class UserDaoImpl implements IUserDao {
	
	
	//On demande à Spring d'injecter un objet (bean) EntityManager
	@PersistenceContext
	private EntityManager em;
	
	

	@Transactional
	@Override
	public void saveAndUpdate(User user) {
		if(user.getId() == 0) {
			em.persist(user);
		}else {
			em.merge(user);
		}
	}
	

	@Transactional(readOnly = true)
	@Override
	public List<User> findAll() {
		String sql = "SELECT u FROM User u";
		TypedQuery<User> req= em.createQuery(sql, User.class);
		
		return req.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public User findUserById(long id) {
		User user = em.find(User.class, id);
		return user;
	}

	@Transactional
	@Override
	public void deleteUser(long id) throws Exception {
		User user = em.find(User.class, id);
		if(user != null) {
			em.remove(user);
		}else {
			throw new Exception("L'utilisateur n'existe pas !!!");
		}
	}

	@Transactional(readOnly = true)
	@Override
	public User findByPasswordAndEmail(String email, String password) {
		String sql = "SELECT u from User u WHERE u.email=: email AND u.password=: password";
		TypedQuery<User>  req = em.createQuery(sql, User.class);
		
		//Remplacer email et password par leur valeur 
		req.setParameter("email", email);
		req.setParameter("password", password);
		
		return req.getSingleResult();
	}

}
