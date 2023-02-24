package fr.dawan.GestionFanClubFrontmvc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.dawan.GestionFanClubFrontmvc.interceptor.LoginInterceptor;

@SpringBootApplication
public class GestionFanClubFrontmvcApplication {
	
	@Autowired
	LoginInterceptor loginInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(GestionFanClubFrontmvcApplication.class, args);
	}
	/*
	 * Spring Boot 2.8.7: Ajout scope runtime pour Thymeleaf dans le pom.xml
	 * 
	 */
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
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
