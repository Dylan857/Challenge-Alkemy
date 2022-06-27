package com.challenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.model.Genero;
import com.challenge.repository.GeneroRepo;

@Service
public class GeneroServiceImpl implements IGeneroService {

	@Autowired
	private GeneroRepo generoRepository;
	
	@Override
	@Transactional
	public Genero crearGenero(Genero genero) {
		return generoRepository.save(genero);
	}

	@Override
	@Transactional(readOnly = true)
	public Genero findGeneroById(Integer id) {
		Optional<Genero> generoOptional = generoRepository.findById(id);
		if(generoOptional.isPresent()) {
			return generoOptional.get();
		} else {
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Genero findGeneroByNombre(String nombre) {
		Optional<Genero> generoOptional = generoRepository.findByNombre(nombre);
		if(generoOptional.isPresent()) {
			return generoOptional.get();
		} else {
			return null;
		}
	}
}
