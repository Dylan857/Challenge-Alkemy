package com.challenge.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.security.jwt.JwtProvider;
import com.challenge.security.model.Rol;
import com.challenge.security.model.Usuario;
import com.challenge.security.service.RolService;
import com.challenge.security.service.UsuarioService;
import com.challenge.service.MailService;
import com.challenge.utils.Mensaje;
import com.challenge.security.dto.JwtDto;
import com.challenge.security.dto.LoginUsuario;
import com.challenge.security.dto.NuevoUsuario;
import com.challenge.security.enums.RolNombre;

@RestController
@CrossOrigin
public class AuthControlador {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RolService rolService;

	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private MailService mailService;

	@PostMapping("/auth/register")
	public ResponseEntity<Usuario> nuevoUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Mensaje(), HttpStatus.BAD_REQUEST);
		} else if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
			return new ResponseEntity(new Mensaje("Email " + "'" + nuevoUsuario.getEmail() + "'" + " ya esta en uso"),HttpStatus.BAD_REQUEST);
		} else if (usuarioService.existsByUsuario(nuevoUsuario.getUsuario())) {
			return new ResponseEntity(new Mensaje("Usuario " + "'" + nuevoUsuario.getUsuario() + "'" + " ya esta en uso"),HttpStatus.BAD_REQUEST);
		}
		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getUsuario(), nuevoUsuario.getEmail(),
				passwordEncoder.encode(nuevoUsuario.getPassword()));
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getRolNombre(RolNombre.ROLE_USER));
		if (nuevoUsuario.getRoles().contains("admin")) {
			roles.add(rolService.getRolNombre(RolNombre.ROLE_ADMIN));
		}
		usuario.setRoles(roles);
		mailService.sendMail(usuario.getEmail(), "Registro exitoso", "Bienvenido nuestra pagina de peliculas Disney usuario: " + usuario.getNombreUsuario());
		return new ResponseEntity<>(usuarioService.guardarUsuario(usuario), HttpStatus.OK);
	}

	@PostMapping("/auth/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		JwtDto jwtDto = new JwtDto(jwt);
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}
}
