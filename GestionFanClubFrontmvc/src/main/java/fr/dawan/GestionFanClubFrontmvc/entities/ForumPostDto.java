package fr.dawan.GestionFanClubFrontmvc.entities;

import java.io.Serializable;

public class ForumPostDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6626867237146940395L;
	private String email;
	
//	private String post;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
}
