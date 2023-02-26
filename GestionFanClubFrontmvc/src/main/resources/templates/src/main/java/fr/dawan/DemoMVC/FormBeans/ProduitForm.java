package fr.dawan.DemoMVC.FormBeans;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class ProduitForm {

	private long id;
	
	@NotEmpty(message = "Ce champs est obligatoire")
	private String description;
	
	@DecimalMin(value = "40", message="Prix min est 40")
	@DecimalMax(value = "999", message="Prix max est 999")
	private double prix;
	
	@Min(1)
	@Max(100)
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

	public ProduitForm(long id, String description, double prix, int quantite) {
		super();
		this.id = id;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
	}

	public ProduitForm(String description, double prix, int quantite) {
		super();
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
	}

	public ProduitForm() {
		super();
	}

}
