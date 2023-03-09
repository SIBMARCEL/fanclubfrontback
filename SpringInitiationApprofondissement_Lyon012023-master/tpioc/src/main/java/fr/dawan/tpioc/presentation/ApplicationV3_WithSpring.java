package fr.dawan.tpioc.presentation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.dawan.tpioc.metier.IMetier;

public class ApplicationV3_WithSpring {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext(new String[] {"config.xml"});
		
		IMetier metier = (IMetier) context.getBean("metier");
		
		System.out.println(metier.calcul());
		
	}

}
