package fr.dawan.gestionfanclub;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.dawan.gestionfanclub.Controller.UserController;
import fr.dawan.gestionfanclub.interceptors.TokenInterceptor;

@SpringBootApplication
public class GestionFanClubApplication {
	
	
	@Autowired
	TokenInterceptor tokenInterceptor;

	public static void main(String[] args) {
		
		//Création du dossier images
		File f = new File(UserController.uploadDirectory);
		
		if(!f.exists()) {
			f.mkdir();
		}
		//new File(UserController.uploadDirectory).mkdir();
		
		
		SpringApplication.run(GestionFanClubApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer mvcConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				//registry.addInterceptor(tokenInterceptor);
			}
			
			//Gestion des applications front
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("*") //list des fronts permis allowedOrigins("http://localhost:3000/")
				.allowedMethods("GET","POST","DELETE","PUT");
			}
		};
	}

}
