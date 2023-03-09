package fr.dawan.springmvc.service;

import java.util.List;

import fr.dawan.springmvc.beans.User;

public interface IUserService {

	public void saveAndUpdate(User user);
	public List<User> findAll();
	public User findUserById(long id);
	public void deleteUser(long id) throws Exception;
	public User findByPasswordAndEmail(String email, String password);
}
