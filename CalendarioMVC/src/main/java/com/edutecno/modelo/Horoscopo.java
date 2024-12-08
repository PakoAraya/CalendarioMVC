package com.edutecno.modelo;

import java.util.Date;

public class Horoscopo {
	private int id;
	private String animal;
	private Date fechaInicio;
	private Date fechaFin;
	
	public Horoscopo() {
		
	}
	
	public Horoscopo(int id, String animal, Date fechaInicio, Date fechaFin) {
		this.id = id;
		this.animal = animal;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public String toString() {
		return "Horoscopo [id=" + id + ", animal=" + animal + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ "]";
	}
}
