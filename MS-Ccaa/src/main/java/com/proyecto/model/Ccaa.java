package com.proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "comunidades")
public class Ccaa {
	@Positive
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long cod;
	@NotNull
	@NotBlank
	@Column(nullable = false, length = 25)
	private String nombre;
	@NotNull
	@PositiveOrZero
	@Column(name = "plazas_cubiertas", nullable = false)
	private int plazasCubiertas;

	public Ccaa() {

	}

	public Ccaa(String nombre, int plazarCubiertas) {
		super();
		this.nombre = nombre;
		this.plazasCubiertas = plazarCubiertas;
	}

	public Ccaa(@NotNull @NotBlank String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPlazasCubiertas() {
		return plazasCubiertas;
	}

	public void setPlazasCubiertas(int plazasCubiertas) {
		this.plazasCubiertas = plazasCubiertas;
	}

	public Long getCod() {
		return cod;
	}

	

}
