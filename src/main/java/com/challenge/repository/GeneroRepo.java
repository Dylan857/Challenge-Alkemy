package com.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.model.Genero;

public interface GeneroRepo extends JpaRepository<Genero, Integer>{

	public Optional<Genero> findByNombre(String nombre);
	
}
