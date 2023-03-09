package fr.dawan.gestioncomptebancaire.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * @Configuration (voir pdf page 72) : Elle est utilisée sur une classe pour indiquer 
 * que son objectif est de fournir des definitions de bean
 * @EnableWebSecurity permet d'activer la secutité web
 */
@Configuration
@EnableWebSecurity
public class BankSecurityConfig extends WebSecurityConfigurerAdapter {

	// Dans cette méthode on va definir les utilisateurs (c'est à dire les rôles,
	// user, et le
	// mot de passe
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*
		 * Avec l'objet AuthenticationManagerBuilder : On va specifier quels sont les
		 * utilisateurs qui ont le droit d'acceder à l'application
		 */
		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("USER", "ADMIN");
		auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER");

	}

	// On va definir les strategies de securité (les regles)
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// On va demander à Spring de passer par une authentification basée sur un
		// formulaire
		http.formLogin();

		// On securise les ressources de l'application en definissant un rôle pour
		// chaque action
		// Ces sont actions sont /bank/operations, bank/consultercompte et
		// /bank/saveOperation
		http.authorizeHttpRequests().antMatchers("/").permitAll();
		http.authorizeHttpRequests().antMatchers("/bank/operations", "/bank/consultercompte").hasRole("USER");
		http.authorizeHttpRequests().antMatchers("/bank/saveOperation").hasRole("ADMIN");

		// Toutes les ressources nécessitent une authentification
		http.authorizeHttpRequests().anyRequest().authenticated();

	}

}
