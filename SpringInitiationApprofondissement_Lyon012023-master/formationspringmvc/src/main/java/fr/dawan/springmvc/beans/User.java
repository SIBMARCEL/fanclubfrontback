package fr.dawan.springmvc.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 3385812398780136955L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	/*
	 * @Version permet de detecter des modifications simultanées sur la même ligne de la table
	 * 
	 * L'élément @Version indique que la table contient des enregistrements versionnés.
	 *  La propriété est incrémentée automatiquement par Hibernate.
	 *  
	 *  A chaque fois qu'il y aura une modification de cet objet, 
	 *  la version de cet objet sera incrémentée de 1 dans la table
	 */
	@Version
	private int version;
	
	private String prenom;
	
	private String nom; 
	
	private String email; 
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role =Role.USER;
	
	public User() {
	}

	public User(String prenom, String nom, String email, String password) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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
	
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getVersion() {
		return version;
	}
	
	
	
}
