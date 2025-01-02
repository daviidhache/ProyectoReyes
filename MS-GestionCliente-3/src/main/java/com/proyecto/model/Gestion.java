package com.proyecto.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "gestiones")
public class Gestion implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private GestionId gestionId;
	@Column(name = "fec_solicitud")
	private LocalDate fechaSolicitud;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Cat categoria;

	public Gestion() {
		super();
	}

	/**
	 * Permite persistir la fecha en caso de que el valor venga null
	 */
	@PrePersist
	protected void onCreate() {
		if (fechaSolicitud == null) {
			fechaSolicitud = LocalDate.now();
		}
	}

	public Gestion(GestionId gestionId, LocalDate fechaSolicitud, Cat categoria) {
		super();
		this.gestionId = gestionId;
		this.fechaSolicitud = fechaSolicitud;
		this.categoria = categoria;
	}

	public GestionId getGestionId() {
		return gestionId;
	}

	public void setGestionId(GestionId gestionId) {
		this.gestionId = gestionId;
	}

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Cat getCategoria() {
		return categoria;
	}

	public void setCategoria(Cat categoria) {
		this.categoria = categoria;
	}

}
