package com.challenge.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.dto.PeliculaDto;
import com.challenge.model.Genero;
import com.challenge.model.Pelicula;
import com.challenge.model.Personaje;
import com.challenge.service.IGeneroService;
import com.challenge.service.IPeliculaService;
import com.challenge.service.IPersonajeService;
import com.challenge.utils.Mensaje;

@RestController
public class PeliculaController {

	@Autowired
	private IPeliculaService peliculaService;
	
	@Autowired
	private IGeneroService generoService;
	
	@Autowired
	private IPersonajeService personajeService;
	
	@GetMapping("/movies")
	public ResponseEntity<List<Pelicula>> peliculas() {
		return new ResponseEntity<>(peliculaService.peliculas(), HttpStatus.OK);
	}
	
	@GetMapping("/movies/detalles/{idPelicula}")
	public ResponseEntity<Pelicula> detallesPelicula(@PathVariable("idPelicula") Integer idPelicula) {
		return new ResponseEntity<>(peliculaService.findPeliculaById(idPelicula), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/movies/crear")
	public ResponseEntity<Pelicula> crearPeliculas(@RequestBody PeliculaDto peliculaDto) {
		Pelicula peliculaNombre = peliculaService.findPeliculaByNombre(peliculaDto.getTitulo());
		if(peliculaNombre != null) {
			return new ResponseEntity(new Mensaje("Esa pelicula ya existe"), HttpStatus.BAD_REQUEST);
		}
		if(peliculaDto.getCalificacion() > 5) {
			return new ResponseEntity(new Mensaje("La calificacion no puede ser mayor a 5"), HttpStatus.BAD_REQUEST);
		}
		Genero genero = generoService.findGeneroById(peliculaDto.getGeneroId());
		Pelicula pelicula = new Pelicula(peliculaDto.getImagenUrl(), peliculaDto.getTitulo(), peliculaDto.getFechaCreacion(), peliculaDto.getCalificacion(), genero);
		List<Integer> personajesIds = peliculaDto.getPersonajes();
		Set<Personaje> personajes = new HashSet<>();
		for(int id: personajesIds) {
			Personaje personaje = personajeService.findPersonajeById(id);
			personajes.add(personaje);
		}
		pelicula.setPersonajes(personajes);
		return new ResponseEntity<>(peliculaService.crearPelicula(pelicula), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/movies/{idMovie}/characters/{idCharacter}")
	public ResponseEntity<Pelicula> agregarPersonajePelicula(@PathVariable("idMovie") Integer idMovie, @PathVariable("idCharacter") Integer idCharacter) {
		Pelicula pelicula = peliculaService.findPeliculaById(idMovie);
		Set<Personaje> personajesPeliculas = pelicula.getPersonajes();
		Personaje personaje = personajeService.findPersonajeById(idCharacter);
		personajesPeliculas.add(personaje);
		pelicula.setPersonajes(personajesPeliculas);
		return new ResponseEntity<>(peliculaService.crearPelicula(pelicula),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/movies/actualizar/{id}")
	public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable("id") Integer id,@RequestBody PeliculaDto peliculaDto) {
		Pelicula peliculaEncontrada = peliculaService.findPeliculaById(id);
		if(peliculaEncontrada == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try {
			Genero genero = generoService.findGeneroById(peliculaDto.getGeneroId());
			List<Integer> personajesIds = peliculaDto.getPersonajes();
			Set<Personaje> personajes = new HashSet<>();
			for(int idPersonaje: personajesIds) {
				Personaje personaje = personajeService.findPersonajeById(idPersonaje);
				personajes.add(personaje);
			}
			peliculaEncontrada.setImagenUrl(peliculaDto.getImagenUrl());
			peliculaEncontrada.setTitulo(peliculaDto.getTitulo());
			peliculaEncontrada.setFechaCreacion(peliculaDto.getFechaCreacion());
			peliculaEncontrada.setCalificacion(peliculaDto.getCalificacion());
			peliculaEncontrada.setPersonajes(personajes);
			peliculaEncontrada.setGenero(genero);
			return new ResponseEntity<>(peliculaService.crearPelicula(peliculaEncontrada), HttpStatus.OK);
		} catch(DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/movies/eliminar/{id}")
	public ResponseEntity<Boolean> eliminarPelicula(@PathVariable("id") Integer id) {
		boolean peliculaEliminada = peliculaService.deletePeliculaById(id);
		if(peliculaEliminada) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/movies/{idMovie}/characters/{idCharacter}")
	public ResponseEntity<Pelicula> eliminarPersonajePelicula(@PathVariable("idMovie") Integer idMovie, @PathVariable("idCharacter") Integer idCharacter) {
		Pelicula pelicula = peliculaService.findPeliculaById(idMovie);
		Personaje personaje = personajeService.findPersonajeById(idCharacter);
		Set<Personaje> peliculaPersonajes = pelicula.getPersonajes();
		for (Personaje personajePelicula: peliculaPersonajes) {
			if(personajePelicula == personaje) {
				peliculaPersonajes.remove(personajePelicula);
			}
		}
		pelicula.setPersonajes(peliculaPersonajes);
		return new ResponseEntity<>(peliculaService.crearPelicula(pelicula), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/movies", params = "name")
	public ResponseEntity<Pelicula> buscarPeliculaByNombre(@RequestParam("name") String nombre) {
		return new ResponseEntity<>(peliculaService.findPeliculaByNombre(nombre), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/movies", params = "genre")
	public ResponseEntity<List<Pelicula>> buscarPeliculaByGenero(@RequestParam("genre") Integer idGenero) {
		Genero genero = generoService.findGeneroById(idGenero);
		return new ResponseEntity<>(peliculaService.findPeliculaByGenero(genero), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/movies", params = "order")
	public ResponseEntity<List<Pelicula>> buscarPeliculaEnOrden(@RequestParam("order") String order) {
		return new ResponseEntity<>(peliculaService.ordenada(order), HttpStatus.OK);
	}
}
