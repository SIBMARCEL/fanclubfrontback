package fr.dawan.DemoMVC.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String description;
	private double prix;
	private int quantite;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Produit(long id, String description, double prix, int quantite) {
		super();
		this.id = id;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
	}
	public Produit(String description, double prix, int quantite) {
		super();
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
	}
	public Produit() {
		super();
	}
	
	
	
	

}
