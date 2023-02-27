package fr.dawan.GestionFanClubFrontmvc.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;





public class User implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5166969487202226067L;
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean admin;
	private LocalDate dateInscription;
	
	
	
	/**
	 * 
	 * @return
	 */
	
	public LocalDate getDateInscription() {
		return dateInscription;
	}
	/**
	 * 
	 * @return
	 */
	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isAdmin() {
		return admin;
	}
	/**
	 * 
	 * @return
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * 
	 * @return
	 */
	
	private String imagePath;
	
	@JsonIgnore
	private String imageBase64;
	
	

	public String getImageBase64() {
		return imageBase64;
	}
	/**
	 * 
	 * @return
	 */
	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * 
	 * @return
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * 
	 * @return
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public User(int id, String firstName, String lastName, String email, String password, String imagePath) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.imagePath = imagePath;
	}

	public User(String firstName, String lastName, String email, String password, String imagePath) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.imagePath = imagePath;
	}

	public User() {
		super();
	}

}
