package com.hacka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hacka.entities.Usuario;
import com.hacka.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping("/criarUsuario")
	public Usuario criarUsuario(@RequestBody Usuario usuario){
		return service.criarUsuario(usuario);
	}
	
	@GetMapping("/login")
	public boolean login(@RequestParam String email, @RequestParam String senha) {
		return service.login(email, senha);
	}
	
	@GetMapping("/loginUsuario")
	public Usuario loginUsuario(@RequestParam String email) {
		Usuario usuario = service.loginUsuario(email);
		return usuario;
	}
}
