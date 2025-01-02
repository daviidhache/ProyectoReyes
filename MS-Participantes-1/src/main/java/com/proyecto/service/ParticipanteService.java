package com.proyecto.service;

import java.time.LocalDate;
import java.util.List;

import com.proyecto.model.Participante;

/**
 * Service de la clase Participante
 * 
 * @author David Hernández Fuentes / MasterJava
 * @version 0.1
 */
public interface ParticipanteService {

	/**
	 * Devuelve una lista con todos los participantes actuales inscritos en la bd
	 * 
	 * @return List<Participante> lita
	 */
	List<Participante> todos();

	/**
	 * Da de alta un nuevo participante en la entidad
	 * 
	 * @param Participante p
	 */
	void nuevoParticipante(Participante p);

	Participante obtenerParticipante(String dni);

	boolean edadValida(LocalDate fechaNacimiento);
}
