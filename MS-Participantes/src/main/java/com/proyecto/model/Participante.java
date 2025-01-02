package com.proyecto.model;

import java.io.Serializable;
/**
 * Clase participante que define los atributos.
 * @author David Hernández Fuentes / MasterJava
 *@version 0.1
 */
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "participantes")
public class Participante implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(nullable = false, length = 9)
	@NotNull
	@NotBlank
	@Size(min = 9, max = 9)
	private String dni;
	@Column(nullable = false, length = 25)
	@NotNull
	@NotBlank
	private String nombre;
	@Column(nullable = false, length = 25)

	@NotNull
	@NotBlank
	private String app1;
	@Column(nullable = false, length = 25)

	@NotNull
	@NotBlank
	private String app2;
	@Column(nullable = false, unique = true, length = 40)

	@NotNull
	@NotBlank
	@Email
	private String email;
	@NotNull
	@Column(name = "fec_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;

	public Participante(String dni, String nombre, String app1, String app2, String email, LocalDate fechaNacimiento) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.app1 = app1;
		this.app2 = app2;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Participante() {
		super();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApp1() {
		return app1;
	}

	public void setApp1(String app1) {
		this.app1 = app1;
	}

	public String getApp2() {
		return app2;
	}

	public void setApp2(String app2) {
		this.app2 = app2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
