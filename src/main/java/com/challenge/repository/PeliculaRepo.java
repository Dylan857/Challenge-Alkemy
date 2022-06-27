package com.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.challenge.model.Genero;
import com.challenge.model.Pelicula;

@Repository
public interface PeliculaRepo extends JpaRepository<Pelicula, Integer>{
	
	public Optional<Pelicula> findByTitulo(String nombre);
	
	public List<Pelicula> findByGenero(Genero idGenero);
	
	@Query(value = "SELECT * FROM pelicula ORDER BY fecha_creacion ASC", nativeQuery = true)
	public List<Pelicula> peliculasASC();
	
	@Query(value = "SELECT * FROM pelicula ORDER BY fecha_creacion DESC", nativeQuery = true)
	public List<Pelicula> peliculasDESC();
}
