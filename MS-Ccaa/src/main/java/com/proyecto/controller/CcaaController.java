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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Ccaa", description = "La API de las Comunidades Autónomas")
@RequestMapping("/ccaa")
public class CcaaController {
	@Autowired
	private CcaaService service;
	@Operation(summary = "Lista Comunidades", description = "Obtiene la lista de todas las Comunidades", responses = {
			@ApiResponse(responseCode = "200", description = "Lista de Comunidades"),
			@ApiResponse(responseCode = "500", description = "Error del servidor"),
			@ApiResponse(responseCode = "404", description = "Error en la petición")

	})
	@GetMapping
	public List<Ccaa> todas() {
		return service.todas();
	}
	@Operation(summary = "Nueva Ccaa", description = "Da de alta una comunidad", responses = {
			@ApiResponse(responseCode = "201", description = "Comunidad creada"),
			@ApiResponse(responseCode = "500", description = "Error del servidor"),
			@ApiResponse(responseCode = "404", description = "Error en la petición")

	})
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
	@Operation(summary = "Comunidad", description = "Obtiene la comunidad por su código", responses = {
			@ApiResponse(responseCode = "200", description = "Encontrada"),
			@ApiResponse(responseCode = "500", description = "Error del servidor"),
			@ApiResponse(responseCode = "404", description = "Error en la petición")

	})
	@GetMapping("/{cod}")
	ResponseEntity<Object> obtener(@PathVariable Long cod) {
		Ccaa ca = service.obtenerComunidad(cod);
		if (ca != null) {
			return ResponseEntity.ok(ca);
		} else {
			return new ResponseEntity<Object>(new String("Comunidad no encontrada"), HttpStatus.NOT_FOUND);
		}
	}
	@Operation(summary = "Comunidad", description = "Obtiene la comunidad a partir de su nombre", responses = {
			@ApiResponse(responseCode = "200", description = "Encontrada"),
			@ApiResponse(responseCode = "500", description = "Error del servidor"),
			@ApiResponse(responseCode = "404", description = "Error en la petición")

	})
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
