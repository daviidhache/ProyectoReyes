package com.proyecto.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Embeddable
public class GestionId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	@Positive
	private Long id;
	@NotNull
	@NotBlank
	@Column(length = 9)
	private String participante;
	@NotNull
	private Long ccaa;

	public GestionId() {
		super();
	}

	public GestionId(Long id, String participante, Long ccaa) {
		super();
		this.id = id;
		this.participante = participante;
		this.ccaa = ccaa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParticipante() {
		return participante;
	}

	public void setParticipante(String participante) {
		this.participante = participante;
	}

	public Long getCcaa() {
		return ccaa;
	}

	public void setCcaa(Long ccaa) {
		this.ccaa = ccaa;
	}

	

}
