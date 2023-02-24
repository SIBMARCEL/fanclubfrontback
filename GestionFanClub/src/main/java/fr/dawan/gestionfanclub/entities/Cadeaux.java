package fr.dawan.gestionfanclub.entities;

import java.io.Serializable;
import fr.dawan.gestionfanclub.enums.TypeCadeaux;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Cadeaux implements Serializable{

	private static final long serialVersionUID = -5976470229745894404L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING)
	private TypeCadeaux typeCadeaux;

}
