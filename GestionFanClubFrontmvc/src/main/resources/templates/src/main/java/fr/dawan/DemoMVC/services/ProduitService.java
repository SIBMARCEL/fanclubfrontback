package fr.dawan.DemoMVC.services;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.DemoMVC.entities.Produit;
import fr.dawan.DemoMVC.repositories.ProduitRepository;

@Service
@Transactional
public class ProduitService implements IProduitService {

	@Autowired
	private ProduitRepository produitRepository;
	
	@Override
	public void Create(Produit p) { 
		produitRepository.save(p);
	}
	
	@Override
	public void Update(Produit p) {
		produitRepository.save(p);
	}
	
	@Override
	public void Delete(Produit p) {
		produitRepository.delete(p);
	}
	
	@Override
	public List<Produit> GetAll(){
		return produitRepository.findAll();
	}
	
	@Override
	public Produit GetById(long id) {
		return produitRepository.findById(id).get();
	}
	
	@Override
	public List<Produit> findByKey(String key){
		return produitRepository.findByKey(key);
	}
}
