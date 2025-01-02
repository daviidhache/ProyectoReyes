package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.model.Ccaa;

/**
 * Service de la clase CCAA
 * 
 * @author David Hernández Fuentes / MasterJava
 * @version 0.1
 */
public interface CcaaService {

	/**
	 * Devuelve una lista con todas las CCAA disponibles
	 * 
	 * @return List<Ccaa> lita
	 */
	List<Ccaa> todas();

	/**
	 * Da de alta una nueva comunidad a la entidad
	 * 
	 * @param Ccaa c
	 */
	void nuevaComunidad(Ccaa p);

	Ccaa obtenerComunidad(String nombre);
	
	Ccaa obtenerComunidad(Long id);
}
