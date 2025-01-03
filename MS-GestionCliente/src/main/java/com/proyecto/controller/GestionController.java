package com.proyecto.controller;

import java.util.List;
import java.util.Map;

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

import com.proyecto.model.Gestion;
import com.proyecto.model.GestionId;
import com.proyecto.model.InfoCcaaDto;
import com.proyecto.service.GestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Gestión solicitudes", description = "La API de la gestión")

@RequestMapping("/gestiones")
public class GestionController {
	@Autowired
	private GestionService service;

	/**
	 * Devuelve todas las gestiones
	 * 
	 * @return List<Gestion> gestiones actuales
	 */
	@Operation(summary = "Lista de solicitudes", responses = { @ApiResponse(responseCode = "200", description = "Ok"),
			@ApiResponse(responseCode = "500", description = "Error del servidor"),
			@ApiResponse(responseCode = "404", description = "Error en la petición")

	})

	@GetMapping
	public List<Gestion> todas() {
		return service.todas();
	}

	@Operation(summary = "Gestion", responses = { @ApiResponse(responseCode = "200", description = "Encontrada"),
			@ApiResponse(responseCode = "500", description = "Error del servidor"),
			@ApiResponse(responseCode = "404", description = "Error en la petición. No encontrada")

	})
	@GetMapping("/{cod}")
	ResponseEntity<Object> obtenerGestion(@PathVariable Long cod) {

		Gestion g = service.obtenerGestion(cod);
		if (g != null) {
			return new ResponseEntity<Object>(g, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Gestión no encontrada", HttpStatus.NOT_FOUND);

		}
	}

	@Operation(summary = "Nueva solicitud", description = "Da de alta una solicitud", responses = {
			@ApiResponse(responseCode = "201", description = "Solicitud creada"),
			@ApiResponse(responseCode = "500", description = "Error del servidor"),
			@ApiResponse(responseCode = "404", description = "Error en la petición")

	})
	@PostMapping("/nueva")
	ResponseEntity<Object> nuevaGestion(@Valid @RequestBody Gestion g, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<Object>(new String("Datos incorrectos"), HttpStatus.NOT_FOUND);
		} else {
			// Para buscar la gestion, hacemos uso de las apis de participante y comunidad
			// para comprobar si estos existen y ser dados de alta.
			try {
				Gestion ges = service.comprobarGestion(g);
				if (ges == null) {
					// No hay solicitudes ya registradas para el participante y comunidad con mismo
					// id
					// 2. Obtenemos el participante y la ccaa obtenidas si existen. Si existen, se
					// crea la gestión
					boolean existen = service.verificarDatos(g);
					if (existen) {
						GestionId.setNumGestiones(GestionId.getNumGestiones() + 1);
						g.getGestionId().setId(GestionId.getNumGestiones());
						service.nuevaGestion(g);

						return new ResponseEntity<Object>("Solicitud creada", HttpStatus.OK);

					} else {
						return new ResponseEntity<Object>(
								new String("Datos del participante o comunidad no existentes"), HttpStatus.NOT_FOUND);

					}
				} else {
					return new ResponseEntity<Object>(
							new String("Ya existe una solicitud para los datos proporcionados"), HttpStatus.NOT_FOUND);

				}
			} catch (Exception e) {
				return new ResponseEntity<Object>("Error. Verifica los datos proporcionados", HttpStatus.NOT_FOUND);

			}

		}

	}

	@Operation(summary = "Lista de solicitudes", description = "Lista de solicitudes por nombre de comunidad", responses = {
			@ApiResponse(responseCode = "200", description = "Ok"),
			@ApiResponse(responseCode = "500", description = "Error del servidor"),
			@ApiResponse(responseCode = "404", description = "Error en la petición")

	})
	@GetMapping("/comunidad/{nombre}")
	public List<Gestion> gestionesPorComunidad(@PathVariable String nombre) {
		return service.gestionesPorComunidad(nombre);
	}

	@GetMapping("/info/{comunidad}")
	public InfoCcaaDto totales(@PathVariable String comunidad) {
		return service.totalesPorComunidad(comunidad);
	}

	@GetMapping("/totales/{comunidad}")
	public Map<String, String> totalesCategoria(@PathVariable String comunidad) {
		return service.totalesCategoriasComunidad(comunidad);

	}
}
