package com.challenge.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.security.enums.RolNombre;
import com.challenge.security.model.Rol;
import com.challenge.security.repository.RolRepo;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepo rolRepository;
	
	@Override
	public Rol getRolNombre(RolNombre rolNombre) {
		Optional<Rol> rolOptional = rolRepository.findByRolNombre(rolNombre);
		if(rolOptional.isPresent()) {
			return rolOptional.get();
		} else {
			return null;
		}
	}
	
}
