package com.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.model.Pelicula;
import com.challenge.model.Personaje;
import com.challenge.repository.PeliculaRepo;
import com.challenge.repository.PersonajeRepo;
import java.util.Collections;

@Service
public class PersonajeServiceImpl implements IPersonajeService {

	
	@Autowired
	private PersonajeRepo personajeRepository;
		
	@Autowired
	private PeliculaRepo peliculaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Personaje> listaPersonajes() {
		return personajeRepository.findAll();
	}

	@Override
	@Transactional
	public Personaje crearPersonaje(Personaje personaje) {
		return personajeRepository.save(personaje);
	}

	
	@Override
	@Transactional(readOnly = true)
	public Personaje findPersonajeById(Integer id) {
		Optional<Personaje> personajeOptional = personajeRepository.findById(id);
		if(personajeOptional.isPresent()) {
			 return personajeOptional.get();
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public Boolean deletePersonajeById(Integer id) {
		try {
			personajeRepository.deleteById(id);
			return true;
		} catch(Exception error) {
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Personaje> findPersonajeByNombre(String nombre) {
		return personajeRepository.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Personaje> findPersonajeByEdad(Integer edad) {
		return personajeRepository.findByEdad(edad);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Personaje> findPersonajeByIdMovie(Integer idMovie) {
		Optional<Pelicula> peliculaOptional = peliculaRepository.findById(idMovie);
		if(peliculaOptional.isPresent()) {
			Pelicula pelicula = peliculaOptional.get();
			return personajeRepository.findByPeliculas(pelicula);
		} else {
			return Collections.emptyList();
		}
		
	}
	
}
