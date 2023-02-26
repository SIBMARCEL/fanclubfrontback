package fr.dawan.DemoMVC.services;

import java.util.List;

import fr.dawan.DemoMVC.entities.Utilisateur;

public interface IUtilisateurService {

	void Create(Utilisateur u);

	void delete(Utilisateur u);

	List<Utilisateur> GetAll();

	Utilisateur GetById(long id);

	Utilisateur FindByEmail(String email);

	void Update(Utilisateur u);

}