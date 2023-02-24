package fr.dawan.gestionfanclub.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Club implements Serializable{

	private static final long serialVersionUID = -4765062768492714189L;
	
	@Id
	private String nomClub;
	
//	@ManyToOne
//	@JoinColumn(name="rubrique")
//	private Rubrique rubrique;
//	
//	@OneToOne
//	//@JoinColumn(name="post")
//	private Post post;
	
	
	private String ville;
}
