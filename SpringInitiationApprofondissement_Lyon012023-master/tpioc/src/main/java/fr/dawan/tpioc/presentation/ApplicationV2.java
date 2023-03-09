package fr.dawan.tpioc.presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Scanner;

import fr.dawan.tpioc.dao.IDao;
import fr.dawan.tpioc.dao.V1.DaoImplV1;
import fr.dawan.tpioc.metier.IMetier;

public class ApplicationV2 {

	public static void main(String[] args) {

		try {
			
			Scanner input = new Scanner(new File("config.txt"));
			
			//Lire le nom de la classe dao
			String daoClassName = input.next();
			System.out.println(daoClassName);
			
			//Lire le nom de la classe metier 
			String metierClassName = input.next();
			System.out.println(metierClassName);
			
			//Création de l'objet dao
			Class cDao = Class.forName(daoClassName);
			IDao dao =  (IDao) cDao.newInstance();
			System.out.println(dao.getValue());
			
			//Création de l'objet metier
			Class cMetier = Class.forName(metierClassName);
			IMetier metier  = (IMetier) cMetier.newInstance();
			
			//Association (Injection de dependance par setter)
			Method m1= cMetier.getMethod("setDao", new Class[] {IDao.class});
			m1.invoke(metier, new Object[] {dao});
			
			System.out.println(metier.calcul());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
