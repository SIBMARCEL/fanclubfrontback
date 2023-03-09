package fr.dawan.gestioncomptebancaire.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.gestioncomptebancaire.entities.Client;

/*
 * Avec SpringData il suffit d'heriter d'une interface générique qui s'appelle JpaRepository
 */
public interface ClientRepository extends JpaRepository<Client, Long>{

}
