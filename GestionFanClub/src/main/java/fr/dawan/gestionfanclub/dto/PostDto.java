package fr.dawan.gestionfanclub.dto;

import java.io.Serializable;

public class PostDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2573091468497439685L;
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
