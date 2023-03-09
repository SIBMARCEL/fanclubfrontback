package fr.dawan.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.springmvc.beans.User;
import fr.dawan.springmvc.dao.IUserDao;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao dao; //dao = null
	
	
	
	public void setDao(IUserDao dao) {
		this.dao = dao;
	}

	@Override
	public void saveAndUpdate(User user) {
		dao.saveAndUpdate(user);
	}

	@Override
	public List<User> findAll() {
		
		return dao.findAll();
	}

	@Override
	public User findUserById(long id) {
		
		return dao.findUserById(id);
	}

	@Override
	public void deleteUser(long id) throws Exception {
		dao.deleteUser(id);
		
	}

	@Override
	public User findByPasswordAndEmail(String email, String password) {
		
		return dao.findByPasswordAndEmail(email, password);
	}

}
