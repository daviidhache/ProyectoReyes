package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.model.Participante;
import com.proyecto.service.ParticipanteService;

import jakarta.validation.Valid;

/**
 * Controlador del recurso Participante
 * 
 * @author David Hernández Fuentes / MasterJava
 * @version 0.1
 */
@RestController
@RequestMapping("participantes")
public class ParticipanteController {
	@Autowired
	private ParticipanteService service;

	@GetMapping
	public List<Participante> todos() {
		return service.todos();
	}

	/**
	 * Da de alta un nuevo participante al sistema
	 * 
	 * @param p
	 * @param br
	 * @return String mensaje
	 *
	 */
	@PostMapping("/nuevo")
	public ResponseEntity<String> altaParticipante(@Valid @RequestBody Participante p, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<String>("Datos incorrectos", HttpStatus.BAD_REQUEST);

		} else {
			Participante pa = service.obtenerParticipante(p.getDni());
			if (pa == null) {
				try {
						if(service.edadValida(p.getFechaNacimiento())) {
							service.nuevoParticipante(p);
							return new ResponseEntity<String>("Participante creado", HttpStatus.OK);

						}else {
							return new ResponseEntity<String>("No entras en el rango de edad aceptado(>= 18 y  <75)", HttpStatus.OK);

						}


				} catch (DataIntegrityViolationException e) {
					return new ResponseEntity<String>("Correo ya existe", HttpStatus.OK);

				}

			} else {
				return new ResponseEntity<String>("Participante ya existe", HttpStatus.BAD_REQUEST);

			}
		}

	}

	/**
	 * Obtiene el participante o mensaje en caso de error
	 * 
	 * @param dni
	 * @return Participante p
	 */
	@GetMapping("/{dni}")
	ResponseEntity<Object> obtener(@PathVariable String dni) {
		Participante p = service.obtenerParticipante(dni);
		if (p != null) {
			return ResponseEntity.ok(p);
		} else {
			return new ResponseEntity<Object>(new String("Participante no encontrado"), HttpStatus.NOT_FOUND);
		}
	}

}
