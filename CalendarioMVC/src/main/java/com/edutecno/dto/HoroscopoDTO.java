package com.edutecno.dto;

import java.util.Date;

import com.edutecno.modelo.Horoscopo;

public class HoroscopoDTO {
	private int id;
	private String animal;
	private Date fechaInicio;
	private Date fechaFin;
	
	//Constructor vacio
	public HoroscopoDTO() {
		
	}
	
	//Constructo basado en el modelo Horoscopo
	public HoroscopoDTO(Horoscopo horoscopo) {
		this.id = horoscopo.getId();
		this.animal = horoscopo.getAnimal();
		this.fechaInicio = horoscopo.getFechaInicio();
		this.fechaFin = horoscopo.getFechaFin();
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
		return "HoroscopoDTO [id=" + id + ", animal=" + animal + ", fechaInicio=" + fechaInicio + ", fechaFin="
				+ fechaFin + "]";
	}
}
