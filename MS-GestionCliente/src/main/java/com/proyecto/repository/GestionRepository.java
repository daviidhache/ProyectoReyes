package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Gestion;

@Repository
public interface GestionRepository extends JpaRepository<Gestion, Long> {
	@Query("FROM Gestion g WHERE  g.gestionId.id = :cod AND g.gestionId.participante = :p AND g.gestionId.ccaa = :c")
	Gestion comprobarGestion(@Param("cod") Long codGestion, @Param("p") String participanteDni, @Param("c") Long nombreComunidad);

	@Query("FROM Gestion g WHERE g.gestionId.id =:cod")
	Gestion obtenerGestion(@Param("cod") Long id);
	
	@Query("FROM Gestion g WHERE g.gestionId.ccaa =:cod")
	List<Gestion> gestionesPorComunidad(@Param("cod") Long cod);
}
