package fr.dawan.DemoMVC.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobelExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String displayErrorPage() {
		return "erreur";
	}
	
	/*
	 * Autre option possible: afficher une page d'erreur pour chaque type d'exception
	 * 
	 * 
	 */
	
//	@ExceptionHandler(NullPointerException.class)
//	public String nullError() {
//		return "nullError";
//	}
}
