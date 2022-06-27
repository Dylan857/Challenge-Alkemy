package com.challenge.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.challenge.model.Pelicula;

public class PersonajeDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String imagenUrl;
	
	private String nombre;
	
	private int edad;
	
	private Float peso;
	
	private String historia;
	
	private Set<Pelicula> peliculas = new HashSet<>();
	
	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public Set<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	
}
