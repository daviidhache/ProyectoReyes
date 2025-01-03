package com.proyecto.model;

public class InfoCcaaDto {
	private String ccaa;
	private int inscritos;

	public InfoCcaaDto() {
		super();
	}
	

	public InfoCcaaDto(String ccaa, int inscritos) {
		super();
		this.ccaa = ccaa;
		this.inscritos = inscritos;
	}


	public String getCcaa() {
		return ccaa;
	}

	public void setCcaa(String ccaa) {
		this.ccaa = ccaa;
	}

	public int getInscritos() {
		return inscritos;
	}

	public void setInscritos(int inscritos) {
		this.inscritos = inscritos;
	}

}
