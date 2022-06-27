package com.challenge.service;

import com.challenge.model.Genero;

public interface IGeneroService {

	public Genero crearGenero(Genero genero);
	
	public Genero findGeneroById(Integer id);
	
	public Genero findGeneroByNombre(String nombre);
}
