package com.demoweb.app.controller;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demoweb.app.model.Usuario;

@Controller
@RequestMapping("/app") // la página aparecerá en localhost:8080/app
public class IndexController {
	
	@Value("${texto.indexcontroller.index.titulo}")
	private String textoIndex;
	@Value("${texto.indexcontroller.perfil.titulo}")
	private String textoPerfil;
	@Value("${texto.indexcontroller.listar.titulo}")
	private String textoListar;

	// anotación GetMapping: indica que el método es del tipo GET
	// existen anotaciones también para POST, PUT, DELETE, etc.
	@GetMapping({"/index", "/home", "/", ""}) // puede ser solo "index" o un arreglo de endpoints para determinar el home
	public String index(Model model) {
		model.addAttribute("titulo", textoIndex);
		return "index";
	}
	
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Tino");
		usuario.setApellido("Reyna");
		usuario.setEmail("tino@mail.com");
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre()));
		return "perfil";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		
		// dos formas de manejo de listas
		// forma 1:
		/*
		 * List<Usuario> usuarios = new ArrayList<>(); usuarios.add(new Usuario("Tino",
		 * "Reyna", "tino@mail.com")); usuarios.add(new Usuario("Gabriela", "Lorentzen",
		 * "gaby@mail.com")); usuarios.add(new Usuario("Dante", "Lorentzen",
		 * "dante.com"));
		 */
		// forma 2:		
		/*
		 * List<Usuario> usuarios = Arrays.asList( new Usuario("Tino", "Reyna",
		 * "tino@mail.com"), new Usuario("Gabriela", "Lorentzen", "gaby@mail.com"), new
		 * Usuario("Dante", "Lorentzen", "dante.com") ); model.addAttribute("usuarios",
		 * usuarios);
		 */
		
		model.addAttribute("titulo", textoListar);
		
		return "listar";
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios(){
		List<Usuario> usuarios = Arrays.asList(
				new Usuario("Tino", "Reyna", "tino@mail.com"),
				new Usuario("Gabriela", "Lorentzen", "gaby@mail.com"),
				new Usuario("Dante", "Lorentzen", "dante.com")
			);
		return usuarios;
	}
	
}
