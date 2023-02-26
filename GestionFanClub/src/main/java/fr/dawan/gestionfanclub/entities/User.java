package fr.dawan.gestionfanclub.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1518112475622845530L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private int nbposts;
	private LocalDate derniereDateCo;
	private LocalDate dateInscription;
	
	@Column (unique =true, nullable = false)
	private String email;
	@Column (nullable = false)
	
	private String ImagePath;
	
	private String firstName;
	@Column (nullable = false)
	private String lastName;
	@Column (unique =true, nullable = true)
	private String pseudo;
	private boolean active;
	
	private boolean admin;
	
	@Column(nullable=false)
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNbposts() {
		return nbposts;
	}

	public void setNbposts(int nbposts) {
		this.nbposts = nbposts;
	}

	public LocalDate getDerniereDateCo() {
		return derniereDateCo;
	}

	public void setDerniereDateCo(LocalDate derniereDateCo) {
		this.derniereDateCo = derniereDateCo;
	}

	public LocalDate getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImagePath() {
		return ImagePath;
	}

	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
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

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(long id, int nbposts, LocalDate derniereDateCo, LocalDate dateInscription, String email,
			String imagePath, String firstName, String lastName, String pseudo, boolean active, boolean admin,
			String password) {
		super();
		this.id = id;
		this.nbposts = nbposts;
		this.derniereDateCo = derniereDateCo;
		this.dateInscription = dateInscription;
		this.email = email;
		ImagePath = imagePath;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pseudo = pseudo;
		this.active = active;
		this.admin = admin;
		this.password = password;
	}

	public User() {
		super();
	}
	
	

	
@OneToMany(mappedBy ="user")
private List<Post> posts;
}

