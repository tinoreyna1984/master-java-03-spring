package com.demoweb.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller // controlador
@RequestMapping("/params") // GET - endpoint: /params
public class EjemploParamsController {
	
	// En @RequestParam los argumentos son:
	// nombre del parámetro, si es requerido y valor por defecto
	// La prueba es desde: http://localhost:8080/params/string?texto=Hola%20que%20tal
	// Si no se pasa parámetros, por defecto arroja: "Sin parámetros..."
	@GetMapping("/string")
	public String param (@RequestParam(name="texto", required=false, defaultValue="Sin parámetros...") String texto , Model model) {
		model.addAttribute("resultado", "Texto recibido: " + texto);
		return "/params/ver";
	}
	
	// probar desde: http://localhost:8080/params/
	@GetMapping("/")
	public String index() {
		return "/params/index";
	}
	
	// Probar con varios parámetros
	@GetMapping("/mix-params")
	public String param(@RequestParam(name="saludo", required=false, defaultValue="Hola hola...") String saludo,
						@RequestParam Integer numero,
						Model model) {
		model.addAttribute("resultado", "Saludo: " + saludo + ", con número " + numero);
		return "params/ver";
	}
	
	// HttpServletRequest
	@GetMapping("/mix-params-request")
	public String param(HttpServletRequest request, Model model) {
		String saludo = request.getParameter("saludo");
		Integer numero = null;
		
		try {
			numero = Integer.parseInt(request.getParameter("numero"));
		} catch (NumberFormatException e) {
			System.err.println("Error: " + e.getMessage());
			numero = 0;
		}
		
		model.addAttribute("resultado", "Saludo: " + saludo + ", con número " + numero);
		return "params/ver";
	}
	
}
