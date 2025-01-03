package com.proyecto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.proyecto.model.CcaaDto;
import com.proyecto.model.Gestion;
import com.proyecto.model.InfoCcaaDto;
import com.proyecto.repository.GestionRepository;

@Service
public class GestionImpl implements GestionService {
	private static final String API_PARTICIPANTES = "http://localhost:8080/participantes";
	private static final String API_CCAA = "http://localhost:9090/ccaa";

	@Autowired
	private GestionRepository repo;

	@Autowired
	RestTemplate template;

	@Override
	public List<Gestion> todas() {

		return repo.findAll();
	}

	@Override
	public void nuevaGestion(Gestion g) {

		repo.save(g);
		
	}

	@Override
	public Gestion modificarGestion(Long id, Gestion g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gestion comprobarGestion(Gestion g) {

		return repo.comprobarGestion(g.getGestionId().getId(), g.getGestionId().getParticipante(),
				g.getGestionId().getCcaa());
	}

	@Override
	public Gestion obtenerGestion(Long id) {

		return repo.obtenerGestion(id);
	}

	/**
	 * Comprueba si existen los datos en las apis de participante y ccaa datos[0] ->
	 * Recoge la información del participante datos[1] -> Recoge la información de
	 * la comunidad
	 */
	@Override
	public boolean verificarDatos(Gestion g) {
		boolean validar = true;

		try {
			Object[] datos = new Object[2];
			datos[0] = g.getGestionId().getParticipante();

			ResponseEntity<Object> response = template.exchange(API_PARTICIPANTES + "/{dni}", HttpMethod.GET,
					HttpEntity.EMPTY, Object.class, datos[0]);
			Object p = response.getBody();
			datos[1] = g.getGestionId().getCcaa();
			ResponseEntity<Object> response2 = template.exchange(API_CCAA + "/{cod}", HttpMethod.GET, HttpEntity.EMPTY,
					Object.class, datos[1]);
			Object ccaa = response2.getBody();
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				validar = false;
			}
		}
		return validar;
	}

	/**
	 * Obtener una lista de solicitudes a partir de una comunidad
	 */
	@Override
	public List<Gestion> gestionesPorComunidad(String comunidad) {
		List<Gestion> lista = new ArrayList<>();
		try {
			Object[] datos = new Object[1];
			datos[0] = comunidad.toLowerCase();
			ResponseEntity<CcaaDto> response = template.exchange(API_CCAA + "/nombre/{nombre}", HttpMethod.GET,
					HttpEntity.EMPTY, CcaaDto.class, datos[0]);
			CcaaDto ccaa = response.getBody();
			lista = repo.gestionesPorComunidad(ccaa.getCod());
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				return lista;
			}
		}

		return lista;
	}

	@Override
	public InfoCcaaDto totalesPorComunidad(String comunidad) {
		InfoCcaaDto info = null;
		try {
			Object[] datos = new Object[1];
			datos[0] = comunidad.toLowerCase();
			ResponseEntity<CcaaDto> response = template.exchange(API_CCAA + "/nombre/{nombre}", HttpMethod.GET,
					HttpEntity.EMPTY, CcaaDto.class, datos[0]);
			CcaaDto ccaa = response.getBody();
			int totalPorComunidad = repo.totalesPorComunidad(ccaa.getCod());
			info = new InfoCcaaDto(ccaa.getNombre(), totalPorComunidad);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				return info;
			}

		}
		return info;

	}
}
