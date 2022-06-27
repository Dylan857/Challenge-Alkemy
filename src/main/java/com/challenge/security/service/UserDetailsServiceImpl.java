package com.challenge.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.challenge.security.model.Usuario;
import com.challenge.security.model.UsuarioPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioService.findByNombreUsuario(username);
		if(usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			return UsuarioPrincipal.build(usuario);
		} else {
			return null;
		}
	}
	
}
