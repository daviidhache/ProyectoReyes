package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, String> {
	Participante findByDni(String dni);
}
