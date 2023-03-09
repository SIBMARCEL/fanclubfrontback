package fr.dawan.tpioc.metier;

import org.springframework.beans.factory.annotation.Autowired;

import fr.dawan.tpioc.dao.IDao;


public class MetierImpl implements IMetier{

	//Interface ==> Couplage faible
	private IDao dao;  //dao = null
	
	
	public MetierImpl() {
		super();
	}

	
	/*
	 * Créer une implementatin pour une classe s'appelle injection de dépendance 
	 * On va injecter une implementation pour cette classe
	 */
	
	//Injetction de dépendance par constructeur
	public MetierImpl(IDao dao) {
		super();
		this.dao = dao;
	}



	@Override
	public double calcul() {
		double temp = dao.getValue(); 
		return temp;
	}


	//Injection  de dépendance par setter
	public void setDao(IDao dao) {
		this.dao = dao;
	}
	
	

}
