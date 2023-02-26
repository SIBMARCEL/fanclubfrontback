package fr.dawan.DemoMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.dawan.DemoMVC.interceptors.LoginInterceptor;

@SpringBootApplication
public class DemoMvcApplication {

	@Autowired
	LoginInterceptor loginInterceptor;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoMvcApplication.class, args);
		
	}
	
	//Ajout de LoginInterceptor dans SpringMvc
	//Récupérer la configuration par defaut du module Spring MVC, pour ajouter l'intercepteur
	
	@Bean
	public WebMvcConfigurer mvcConfigurer() {
		
		return new WebMvcConfigurer() {
			
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(loginInterceptor);
			}
			
		};
		
	}

}
/*
 * Maven - Gradle: outils de build (de construction):
 * 
 * - Génère la structure
 * - Gère les dépendences
 * - Compilation - exécution des tests unitaires - géneration de la docs - Génération du livrable (packaging) 
 *  Installation du projet dans le repo local de maven
 *  
 *  Pour utiliser les layouts de Thymeleaf il faut ajouter une dépendence dans maven:
 * 
 * 
 */
