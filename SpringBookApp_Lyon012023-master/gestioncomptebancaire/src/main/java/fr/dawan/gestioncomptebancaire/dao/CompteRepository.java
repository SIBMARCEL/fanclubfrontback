package fr.dawan.gestioncomptebancaire.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.gestioncomptebancaire.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String>{

}
