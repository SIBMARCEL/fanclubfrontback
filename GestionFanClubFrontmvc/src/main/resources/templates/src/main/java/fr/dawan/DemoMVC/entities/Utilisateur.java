package fr.dawan.DemoMVC.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;

@Entity
public class Utilisateur implements Serializable{
	
	/*
	 * LOB: Large Object
	 * CLOB: Character Large Objet (texte large - contenu d'un livre)
	 * BLOB: Binary Large Object (image, vidéos, son)
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	private String password;
	private boolean admin;
	
	@Lob
	@Column(name = "photo", columnDefinition = "MEDIUMBLOB")
	private byte[] photo;
	
	@Transient
	private String base64Image;
	
	

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Utilisateur(long id, String email, String password, boolean admin, byte[] photo) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.admin = admin;
		this.photo = photo;
	}

	public Utilisateur(String email, String password, boolean admin, byte[] photo) {
		super();
		this.email = email;
		this.password = password;
		this.admin = admin;
		this.photo = photo;
	}

	public Utilisateur() {
		super();
	}
	
	

}
