package fr.dawan.gestionfanclub.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.gestionfanclub.dto.*;

import fr.dawan.gestionfanclub.service.ServiceUserImpl;


@RestController
public class AuthController {
	
	@Autowired
	private ServiceUserImpl userService;
	
	@GetMapping("login")
	public String connexion() {
		return "login";
	}
	
	@PostMapping(value="/login", consumes="application/json", produces="application/json")
	public LoginResponseDTO CheckLogin(@RequestBody LoginDTO loginDto) throws Exception {
		return userService.CheckLogin(loginDto);
	}

}
