package com.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.model.Genero;
import com.challenge.model.Pelicula;
import com.challenge.repository.PeliculaRepo;

@Service
public class PeliculaServiceImpl implements IPeliculaService {

	@Autowired
	private PeliculaRepo peliculaRepository;
	
	public List<Pelicula> peliculas() {
		return peliculaRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Pelicula findPeliculaById(Integer id) {
		 Optional<Pelicula> peliculaOptional = peliculaRepository.findById(id);
		if(peliculaOptional.isPresent()) {
			return peliculaOptional.get();
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public Pelicula crearPelicula(Pelicula pelicula) {
		return peliculaRepository.save(pelicula);
	}

	@Override
	@Transactional
	public Boolean deletePeliculaById(Integer id) {
		try {
			peliculaRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Pelicula findPeliculaByNombre(String nombre) {
		Optional<Pelicula> peliculaOptional = peliculaRepository.findByTitulo(nombre);
		if(peliculaOptional.isPresent()) {
			return peliculaOptional.get();
		} else {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pelicula> findPeliculaByGenero(Genero idGenero) {
		return peliculaRepository.findByGenero(idGenero);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pelicula> ordenada(String order) {
		if(order.equals("ASC")) {
			return peliculaRepository.peliculasASC();
		} else if(order.equals("DESC")) {
			return peliculaRepository.peliculasDESC();
		} else {
			return null;
		}
	}
	
	
}
