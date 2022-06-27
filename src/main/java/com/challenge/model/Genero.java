package com.challenge.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Genero implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_genero")
	private Integer idGenero;
	
	private String nombre;
	
	private String imagenUrl;
	
	@OneToMany(mappedBy = "genero")
	private List<Pelicula> peliculas;
	
	public Genero() {
	}

	public Genero(String nombre, String imagenUrl) {
		this.nombre = nombre;
		this.imagenUrl = imagenUrl;
	}

	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIdGenero() {
		return idGenero;
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
