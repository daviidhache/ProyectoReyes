package com.proyecto.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Participante;
import com.proyecto.repository.ParticipanteRepository;

@Service
public class ParticipanteImpl implements ParticipanteService {
	@Autowired
	private ParticipanteRepository repo;

	/**
	 * Devuelve lista de todos los participantes
	 */
	@Override
	public List<Participante> todos() {
		return repo.findAll();
	}

	@Override
	public void nuevoParticipante(Participante p) {
		repo.save(p);
	}

	@Override
	public Participante obtenerParticipante(String dni) {
		return repo.findByDni(dni);
	}

	/**
	 * Método que comprueba si la edad es permitida para aparticipar.
	 */
	@Override
	public boolean edadValida(LocalDate fechaNacimiento) {
		boolean valido = false;
		LocalDate hoy = LocalDate.now();
		int edad = Period.between(fechaNacimiento, hoy).getYears();
		valido = (edad >= 18 && edad < 75 ? true : false);
		return valido;
	}

}
