package com.challenge.security.service;

import java.util.Optional;

import com.challenge.security.model.Usuario;

public interface UsuarioService {

	public Optional<Usuario> findByNombreUsuario(String usuario);
	
	public boolean existsByUsuario(String usuario);
		
	public boolean existsByEmail(String email);
	
	public Usuario guardarUsuario(Usuario usuario);
	
}
