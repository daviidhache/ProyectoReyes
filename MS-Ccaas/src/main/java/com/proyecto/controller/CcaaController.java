package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.model.Ccaa;
import com.proyecto.service.CcaaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ccaa")
public class CcaaController {
	@Autowired
	private CcaaService service;

	@GetMapping
	public List<Ccaa> todas() {
		return service.todas();
	}

	@PostMapping("/nueva")
	public ResponseEntity<String> altaCcaa(@Valid @RequestBody Ccaa c, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<String>("Datos incorrectos", HttpStatus.BAD_REQUEST);

		} else {
			Ccaa ca = service.obtenerComunidad(c.getNombre().toLowerCase());
			if (ca == null) {
				
				service.nuevaComunidad(c);
				return new ResponseEntity<String>("Comunidad creada", HttpStatus.CREATED);

			} else {
				return new ResponseEntity<String>("Comunidad ya existe", HttpStatus.BAD_REQUEST);

			}
		}

	}

	@GetMapping("/{cod}")
	ResponseEntity<Object> obtener(@PathVariable Long cod) {
		Ccaa ca = service.obtenerComunidad(cod);
		if (ca != null) {
			return ResponseEntity.ok(ca);
		} else {
			return new ResponseEntity<Object>(new String("Comunidad no encontrada"), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/nombre/{nombre}")
	ResponseEntity<Object> obtener(@PathVariable String nombre) {
		Ccaa ca = service.obtenerComunidad(nombre);
		if (ca != null) {
			return ResponseEntity.ok(ca);
		} else {
			return new ResponseEntity<Object>(new String("Comunidad no encontrada"), HttpStatus.NOT_FOUND);
		}
	}
}
