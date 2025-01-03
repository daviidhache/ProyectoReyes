package com.proyecto.service;

import java.util.List;

import com.proyecto.model.Gestion;
import com.proyecto.model.InfoCcaaDto;

/**
 * Definimos los métodos en este servicio
 * 
 * @author David Hernández Fuentes / MasterJava
 * @version 0.1
 */
public interface GestionService {
	/**
	 * Listar las peticiones abiertas
	 */
	List<Gestion> todas();

	/**
	 * Nueva gestión
	 */
	void nuevaGestion(Gestion g);

	/**
	 * Actualizar cualquier dato de la gestión
	 * 
	 * @param Long    id
	 * @param Gestion g
	 * @return Gestion gestion
	 */
	Gestion modificarGestion(Long id, Gestion g);

	Gestion comprobarGestion(Gestion g);

	Gestion obtenerGestion(Long id);

	boolean verificarDatos(Gestion g);
	
	List<Gestion> gestionesPorComunidad(String comunidad);
	InfoCcaaDto totalesPorComunidad(String comunidad);
}
