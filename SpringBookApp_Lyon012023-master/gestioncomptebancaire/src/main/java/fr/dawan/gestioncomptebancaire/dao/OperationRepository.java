package fr.dawan.gestioncomptebancaire.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.dawan.gestioncomptebancaire.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long>{

	/*
	 * On specifie que numCpte correspond au paramètre x avec l'annotation @Param("x")
	 */
	@Query("SELECT op FROM Operation op WHERE op.compte.numCompte=:x order by op.numOperation desc")
	Page<Operation> listOperation(@Param("x") String numCpte, Pageable pageable);
	
}
