package fr.dawan.gestionfanclub.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Rubrique implements Serializable {

	private static final long serialVersionUID = -4444031264227119066L;
	
	@Id
	private String nom;
	
	private LocalDate dateCreation;

//	
//	@OneToMany(mappedBy = "rubrique")
//	private List <Club> club;
//	

	private int ordre;
}
