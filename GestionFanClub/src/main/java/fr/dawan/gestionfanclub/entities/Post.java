package fr.dawan.gestionfanclub.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Post implements Serializable {

	private static final long serialVersionUID = 7691919888792374537L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate datePublication;
	
//	@OneToOne(mappedBy = "post")
//	private Club club;
	
//	@ManyToOne 
//	private User user;
	
	private String contenu;
}
