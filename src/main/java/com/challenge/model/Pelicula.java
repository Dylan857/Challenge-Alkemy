package com.challenge.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Pelicula implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pelicula")
	private Integer idPelicula;
	
	private String imagenUrl;
	
	private String titulo;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fechaCreacion;
	
	@Size(min = 1, max = 5)
	private int calificacion;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonBackReference(value = "personaje-pelicula")
	@JoinTable(
			name = "pelicula_personaje",
			joinColumns = @JoinColumn(name = "pelicula_id", referencedColumnName = "id_pelicula"),
			inverseJoinColumns = @JoinColumn(name = "personaje_id", referencedColumnName = "id_personaje")
			)
	private Set<Personaje> personajes = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "genero_id", referencedColumnName = "id_genero")
	@JsonBackReference(value = "genero-pelicula")
	private Genero genero;
	
	public Pelicula() {
	}

	public Pelicula(String imagenUrl, String titulo,  Date fechaCreacion, @Size(min = 1, max = 5) int calificacion, Genero genero) {
		super();
		this.imagenUrl = imagenUrl;
		this.titulo = titulo;
		this.fechaCreacion = fechaCreacion;
		this.calificacion = calificacion;
		this.genero = genero;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIdPelicula() {
		return idPelicula;
	}

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

	public Set<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(Set<Personaje> personajes) {
		this.personajes = personajes;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
}
