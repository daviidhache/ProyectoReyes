package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Ccaa;
import com.proyecto.repository.CcaaRepository;

@Service
public class CcaaImpl implements CcaaService {
	@Autowired
	private CcaaRepository repo;

	@Override
	public List<Ccaa> todas() {

		return repo.findAll();
	}

	@Override
	public void nuevaComunidad(Ccaa p) {
		repo.save(p);

	}

	@Override
	public Ccaa obtenerComunidad(String nombre) {
		return repo.findByNombre(nombre);
	}

	@Override
	public Ccaa obtenerComunidad(Long id) {

		return repo.findById(id).orElse(null);
	}

}
