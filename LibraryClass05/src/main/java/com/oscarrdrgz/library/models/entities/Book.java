package com.oscarrdrgz.library.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//Modelo, entidad de negocio, abstrase de la realidad
@Entity(name="book")//nombre de la tabla en base de datos
public class Book {
	@Id
	@Column(name="isbn")//no es necesario porque lo puede mapear
	private String isbn;
	@Column(name="title")//no es necesario porque lo puede mapear
	private String title;
	
	public Book() {
		super();
	}
	
	public Book(String isbn, String title) {
		super();
		this.isbn = isbn;
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
