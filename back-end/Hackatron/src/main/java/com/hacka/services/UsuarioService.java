package com.hacka.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hacka.entities.Usuario;
import com.hacka.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario criarUsuario(Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return repo.save(usuario);
	}
	
	public boolean login(String email, String senha) {
		Optional<Usuario> login = repo.login(email);
		if(login.isEmpty()) {
			return false;
		}
		Usuario usuario = login.get();
		boolean valid = encoder.matches(senha, usuario.getSenha());
		return valid;
		
	}
	
	public Usuario loginUsuario(String email) {
		return repo.login(email).get();
	}
}
