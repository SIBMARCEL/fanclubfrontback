package fr.dawan.DemoMVC.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println(">>>>>>>>>>> LoginInterceptor");
		System.out.println(request.getRequestURI());

		
		//Intercepetor: intercepeter les requêtes http, avant qu'elles ne soinet traitées par nos controllers
		
		//Vérifier si un user est connecté ou pas - vérifier le contenu de la session
		
		if(request.getRequestURI().contains("/utilisateurs/")) {
			//Cette URI n'est accéssible qu'aux users connectés
			
			if(request.getSession().getAttribute("user") == null) {
				//User non connecté - Redirection vers la page de connexion
				
				response.sendRedirect(request.getContextPath()+"/login");
				
			}
		}
		
		return true; // La requête sera transmise aux controllers MVC
	}

	
}
