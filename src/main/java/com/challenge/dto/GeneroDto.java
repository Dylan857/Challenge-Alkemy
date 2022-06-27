package com.challenge.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.challenge.model.Pelicula;

public class GeneroDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private String imagenUrl;
	
	private List<Pelicula> peliculas = new ArrayList<>();

	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	
}
