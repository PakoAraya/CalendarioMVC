package com.edutecno.dto;

import java.util.Date;

import com.edutecno.modelo.Usuario;

public class UsuarioDTO {
	private int id;
	private String nombre;
	private String username;
	private String email;
	private Date fechaNacimiento;
	private String password;
	private String animal;
	private HoroscopoDTO horoscopoDTO;
	
	//Constructor vacio
	public UsuarioDTO() {
		
	}
	
	//Constructor basado en el modelo Usuario
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nombre = usuario.getNombre();
		this.username = usuario.getUsername();
		this.email = usuario.getEmail();
		this.fechaNacimiento = usuario.getFechaNacimiento();
		this.password = usuario.getPassword();
		this.animal = usuario.getAnimal();
		
		//Convertir el modelo Horoscopo a HoroscopoDTO, si existe
		if(usuario.getHoroscopo() != null) {
			this.horoscopoDTO = new HoroscopoDTO(usuario.getHoroscopo());
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public HoroscopoDTO getHoroscopoDTO() {
		return horoscopoDTO;
	}

	public void setHoroscopoDTO(HoroscopoDTO horoscopoDTO) {
		this.horoscopoDTO = horoscopoDTO;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nombre=" + nombre + ", username=" + username + ", email=" + email
				+ ", fechaNacimiento=" + fechaNacimiento + ", password=" + password + ", animal=" + animal
				+ ", horoscopoDTO=" + horoscopoDTO + "]";
	}
}
