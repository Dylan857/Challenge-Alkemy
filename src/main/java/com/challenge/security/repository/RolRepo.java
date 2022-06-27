package com.challenge.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.security.enums.RolNombre;
import com.challenge.security.model.Rol;

@Repository
public interface RolRepo extends JpaRepository<Rol, Integer>{

	public Optional<Rol> findByRolNombre(RolNombre rolNombre);
	
}
