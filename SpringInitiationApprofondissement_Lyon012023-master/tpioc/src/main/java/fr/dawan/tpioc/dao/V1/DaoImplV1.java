package fr.dawan.tpioc.dao.V1;

import fr.dawan.tpioc.dao.IDao;

public class DaoImplV1 implements IDao{

	@Override
	public double getValue() {
		//Se connecte à la base de données pour récuperer la temperature 
		double data = 5; 
		return data;
	}
	
	
}
