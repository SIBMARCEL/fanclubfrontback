package fr.dawan.gestionfanclub.interceptors;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import fr.dawan.gestionfanclub.*;
import fr.dawan.gestionfanclub.tools.JwtTokenTool;
import fr.dawan.gestionfanclub.tools.TokenSaver;

@Component
public class TokenInterceptor implements HandlerInterceptor{
	
	@Autowired
	JwtTokenTool jwtTokenTool;
	
	@Value("${jwt.secret}")
	private String secret;
	
private List<String> urls = new ArrayList<>();
	
	public List<String> PubliquesRessources(){
		urls.add("/login");
		urls.add("/api/users/save");
		urls.add("/v3/api-docs/");
		urls.add("/swagger-ui.html");
		
		return urls;
	}
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("test intercepteur");
		System.out.println(request.getRequestURI());
		//Si la ressource n'est pas publique, on doit vérifier le token
		
		if(!PubliquesRessources().contains(request.getRequestURI())) {
			
			String header = request.getHeader("Authorization");
			
			if(header == null || header.trim().equals("") || header.length() < 7) {
				//throw new Exception("Token invalide");
				response.setStatus(403);
				response.getWriter().write("Token invalide");
				return false;
			}
			
			String token = header.substring(7); //suppression du mot Bearer suivi d'un espace (7 char)
			
			if(jwtTokenTool.isTokenExpired(token)) {
				//throw new Exception("Token invalide");
				response.setStatus(403);
				response.getWriter().write("Token invalide");
				return false;
			};
			
			String email = jwtTokenTool.getUsernameFromToken(token);
			if(!TokenSaver.tokenByEmail.containsKey(email) || !TokenSaver.tokenByEmail.get(email).equals(token)) {
				//throw new Exception("Token invalide");
				response.setStatus(403);
				response.getWriter().write("Token invalide");
				return false;
			}
		}
		
		
		return true;
	}
	
	
	
	

}
