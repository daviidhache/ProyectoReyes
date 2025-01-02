package com.proyecto.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Ccaa;
@Repository
public interface CcaaRepository extends JpaRepository<Ccaa, Long> {
		Ccaa findByNombre(String nombre);
}
