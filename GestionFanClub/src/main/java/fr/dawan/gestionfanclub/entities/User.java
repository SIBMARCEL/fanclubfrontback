package fr.dawan.gestionfanclub.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.dawan.gestionfanclub.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
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
	
	private String nom;
	@Column (nullable = false)
	private String prenom;
	@Column (unique =true, nullable = false)
	private String pseudo;
	private boolean active;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Column(nullable=false)
	private String password;
	private String imgprofil;
	
//	@OneToMany(mappedBy ="user")
//	private List<Post> posts;
}

