package fr.dawan.DemoMVC.services;

import java.util.List;

import fr.dawan.DemoMVC.entities.Produit;

public interface IProduitService {

	void Create(Produit p);

	void Update(Produit p);

	void Delete(Produit p);

	List<Produit> GetAll();

	Produit GetById(long id);

	List<Produit> findByKey(String key);

}