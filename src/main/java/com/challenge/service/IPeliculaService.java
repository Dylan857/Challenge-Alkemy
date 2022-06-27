package com.challenge.service;

import java.util.List;

import com.challenge.model.Genero;
import com.challenge.model.Pelicula;

public interface IPeliculaService {

	/**
	 * Metodo para obtener una lista de peliculas 
	 * Obtiene todos los registros de la tabla "pelicula"
	 * @return Retorna una lista de peliculas
	 */
	public List<Pelicula> peliculas();
	
	public Pelicula findPeliculaById(Integer id);
	
	public Pelicula crearPelicula(Pelicula pelicula);
	
	public Boolean deletePeliculaById(Integer id);
	
	public Pelicula findPeliculaByNombre(String nombre);
	
	public List<Pelicula> findPeliculaByGenero(Genero idGenero);
	
	public List<Pelicula> ordenada(String order);
	
}
