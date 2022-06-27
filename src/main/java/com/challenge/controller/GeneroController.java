package com.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.dto.GeneroDto;
import com.challenge.model.Genero;
import com.challenge.service.IGeneroService;
import com.challenge.utils.Mensaje;

@RestController
@RequestMapping("/genero")
public class GeneroController {

	@Autowired
	private IGeneroService generoService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/nuevo")
	public ResponseEntity<Genero> crearGenero(@RequestBody GeneroDto generoDto) {
		Genero generoEncontrado = generoService.findGeneroByNombre(generoDto.getNombre());
		if(generoEncontrado != null) {
			return new ResponseEntity(new Mensaje("Ese genero ya existe"), HttpStatus.BAD_REQUEST);
		}
		Genero genero = new Genero(generoDto.getNombre(), generoDto.getImagenUrl());
		return new ResponseEntity<>(generoService.crearGenero(genero), HttpStatus.OK);
	}
	
}
