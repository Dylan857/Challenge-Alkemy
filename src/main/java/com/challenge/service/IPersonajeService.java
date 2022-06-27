package com.challenge.service;

import java.util.List;

import com.challenge.model.Personaje;

public interface IPersonajeService {

	
	public List<Personaje> listaPersonajes();
	
	public Personaje crearPersonaje(Personaje personaje);
	
	public Personaje findPersonajeById(Integer id);
	
	public Boolean deletePersonajeById(Integer id);
	
	public List<Personaje> findPersonajeByNombre(String nombre);
	
	public List<Personaje> findPersonajeByEdad(Integer edad);
	
	public List<Personaje> findPersonajeByIdMovie(Integer idMovie);
	
}
