package com.challenge.controller;

import java.util.List;
import java.util.Set;

import javax.ws.rs.core.MediaType;

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

import com.challenge.dto.PersonajeDto;
import com.challenge.model.Pelicula;
import com.challenge.model.Personaje;
import com.challenge.service.IPersonajeService;

@RestController
public class PersonajesControlador {

	@Autowired
	private IPersonajeService personajeService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/characters/nuevo", consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Personaje> crearPersonaje(@RequestBody PersonajeDto personajeDto) {
		Personaje personaje = new Personaje(personajeDto.getImagenUrl(), personajeDto.getNombre(), personajeDto.getEdad(), personajeDto.getPeso(), personajeDto.getHistoria());
		Set<Pelicula> peliculas = personajeDto.getPeliculas();
		personaje.setPeliculas(peliculas);
		return new ResponseEntity<>(personajeService.crearPersonaje(personaje), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/characters/actualizar/{id}")
	public ResponseEntity<Personaje> actualizarPersonaje(@PathVariable("id") Integer id, @RequestBody PersonajeDto personajeDto) {
		Personaje personajeEncontrado = personajeService.findPersonajeById(id);
		if(personajeEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try {
			personajeEncontrado.setNombre(personajeDto.getNombre());
			personajeEncontrado.setEdad(personajeDto.getEdad());
			personajeEncontrado.setPeso(personajeDto.getPeso());
			personajeEncontrado.setHistoria(personajeDto.getHistoria());
			personajeEncontrado.setPeliculas(personajeDto.getPeliculas());
			personajeEncontrado.setImagenUrl(personajeDto.getNombre());
			return new ResponseEntity<>(personajeService.crearPersonaje(personajeEncontrado), HttpStatus.OK);
		} catch(DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/characters/detalles/{id}")
	public ResponseEntity<Personaje> detallesPersonaje(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(personajeService.findPersonajeById(id),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/characters/eliminar/{id}")
	public ResponseEntity<Boolean> eliminarPersonaje(@PathVariable("id") Integer id) {
		boolean personajeEliminado = personajeService.deletePersonajeById(id);
		if(personajeEliminado) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/characters", params = "name")
	public ResponseEntity<List<Personaje>> encontrarPersonajeByNombre(@RequestParam("name") String nombre) {
		return new ResponseEntity<>(personajeService.findPersonajeByNombre(nombre), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/characters", params = "age")
	public ResponseEntity<List<Personaje>> encontrarPersonajeByEdad(@RequestParam("age") int edad) {
		return new ResponseEntity<>(personajeService.findPersonajeByEdad(edad), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/characters", params = "movies")
	public ResponseEntity<List<Personaje>> encontrarPersonajeByIdMovie(@RequestParam("movies") Integer idMovie) {
		return new ResponseEntity<>(personajeService.findPersonajeByIdMovie(idMovie), HttpStatus.OK);
	}
	
	@GetMapping("/characters")
	public ResponseEntity<List<Personaje>> todosLosPersonajes() {
		return new ResponseEntity<>(personajeService.listaPersonajes(), HttpStatus.OK);
	}
}
