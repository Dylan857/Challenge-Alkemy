package com.challenge.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.model.Pelicula;
import com.challenge.model.Personaje;

@Repository
public interface PersonajeRepo extends JpaRepository<Personaje, Integer>{

	public List<Personaje> findByNombre(String nombre);
	
	public List<Personaje> findByEdad(Integer edad);
	
	public List<Personaje> findByPeliculas(Pelicula pelicula);
}
