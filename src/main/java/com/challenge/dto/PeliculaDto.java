package com.challenge.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;


public class PeliculaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String imagenUrl;
	
	private String titulo;
	
	private Date fechaCreacion;
	
	@Size(min = 1, max = 5)
	private int calificacion;
	
	private List<Integer> personajes = new ArrayList<>();
	
	private Integer generoId;

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public List<Integer> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Integer> personajes) {
		this.personajes = personajes;
	}

	public Integer getGeneroId() {
		return generoId;
	}

	public void setGeneroId(Integer generoId) {
		this.generoId = generoId;
	}
	
	
}
