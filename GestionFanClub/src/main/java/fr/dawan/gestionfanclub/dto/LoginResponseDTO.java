package fr.dawan.gestionfanclub.dto;

import java.io.Serializable;

public class LoginResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7064806971172973893L;
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String imagePath;
	private String token;
	
	
	
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
