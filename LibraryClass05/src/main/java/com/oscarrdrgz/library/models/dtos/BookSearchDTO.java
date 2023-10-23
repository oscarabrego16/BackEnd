package com.oscarrdrgz.library.models.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class BookSearchDTO {
	//POJO Plain Old Java Object, objetos planos para almacenar datos
	@NotBlank(message= "ISBN VACIO")
	@Size(min=10,max=10)
	private String isbn;
	@NotBlank(message= "Persona VACIA")
	@Size(min=2,max=20)
	private String person;
	//mismos nombres aunque se puede mapear
	public BookSearchDTO(String isbn, String person) {
		super();
		this.isbn = isbn;
		this.person = person;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	
	//alt shift s
	
	

}
