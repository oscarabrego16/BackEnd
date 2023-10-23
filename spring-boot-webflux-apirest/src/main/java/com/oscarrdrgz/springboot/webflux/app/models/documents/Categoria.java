package com.oscarrdrgz.springboot.webflux.app.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Document(collection = "categorias")
public class Categoria {
	@Id
	@NotEmpty
	private String id;
	@NotEmpty
	@NotNull
	private String nombre;
	
	public Categoria() {
	}

	public Categoria(String nombre) {
		this.nombre = nombre;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
}
