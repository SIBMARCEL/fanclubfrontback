package fr.dawan.tpioc.presentation;

import fr.dawan.tpioc.dao.V1.DaoImplV1;
import fr.dawan.tpioc.metier.MetierImpl;

public class ApplicationV1 {

	public static void main(String[] args) {

		MetierImpl metier = new MetierImpl();
		
		DaoImplV1 dao = new DaoImplV1();
		
		//Association (Injection dependance par setter)
		metier.setDao(dao);
		
		System.out.println(metier.calcul());
		
	}

}
