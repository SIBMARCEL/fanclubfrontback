package fr.dawan.GestionFanClubFrontmvc.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


	@Component
	public class LoginInterceptor implements HandlerInterceptor{

		
		
		
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			//Vérifier si un user est connecté
			
			System.out.println(">>>>>>>>>>>>Interceptor.............");
			System.out.println(request.getRequestURI());
			System.out.println("Request Context: "+request.getContextPath());
			
			if(request.getRequestURI().contains("/users")) {
				if(request.getSession().getAttribute("loginResponseDto") == null) {
					
					//Aucun user connecté - Afficher la page login
					//request.getContextPath(): renvoie le context de l'application - Url de base: http://localhost:8080
				
					response.sendRedirect(request.getContextPath()+"/login");
					return false;
				}
			}
			return true;
		}

		
	}



