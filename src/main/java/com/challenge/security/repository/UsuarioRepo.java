package com.challenge.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.security.model.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer>{

	public Optional<Usuario> findByNombreUsuario(String usuario);

	public boolean existsByNombreUsuario(String usuario);

	public boolean existsByEmail(String email);

}
