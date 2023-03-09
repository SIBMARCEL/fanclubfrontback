package fr.dawan.springmvc.dao;

import java.util.List;

import fr.dawan.springmvc.beans.User;

public interface IUserDao {
	
	public void saveAndUpdate(User user);
	public List<User> findAll();
	public User findUserById(long id);
	public void deleteUser(long id) throws Exception;
	public User findByPasswordAndEmail(String email, String password);

}
