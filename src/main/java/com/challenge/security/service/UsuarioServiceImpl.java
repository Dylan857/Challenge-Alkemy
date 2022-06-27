package com.challenge.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.security.repository.UsuarioRepo;
import com.challenge.security.model.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepo usuarioRepository; 
	
	@Transactional(readOnly = true)
	public Optional<Usuario> findByNombreUsuario(String usuario) {
		return usuarioRepository.findByNombreUsuario(usuario);
	}
	
	@Transactional(readOnly = true)
	public boolean existsByUsuario(String usuario) {
		return usuarioRepository.existsByNombreUsuario(usuario);
	}
	
	@Transactional(readOnly = true)
	public boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
	
	@Transactional
	public Usuario guardarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
}
