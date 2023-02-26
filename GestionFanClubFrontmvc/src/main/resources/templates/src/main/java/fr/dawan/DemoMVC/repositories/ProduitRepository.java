package fr.dawan.DemoMVC.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.DemoMVC.entities.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>{
	
	@Query("FROM Produit p WHERE p.description LIKE  %:key%") // %key% - HQL (Concat) - JPQL %param%
	List<Produit> findByKey(@Param("key") String key);

}
