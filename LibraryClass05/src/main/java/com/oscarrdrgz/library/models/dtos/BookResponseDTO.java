package com.oscarrdrgz.library.models.dtos;

import java.util.List;

public class BookResponseDTO {
	private String title;
	private String person;
	private List<String> isbns;
	
	public BookResponseDTO(String title, String person, List<String> isbns) {
		super();
		this.title = title;
		this.person = person;
		this.isbns = isbns;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	
	public boolean isPersonCorrect() {
		return !this.person.isBlank();
	}
	public boolean isTitleCorrect() {
		return !this.title.isBlank();
	}
	public List<String> getIsbns() {
		return isbns;
	}
	public void setIsbns(List<String> isbns) {
		this.isbns = isbns;
	}
	
	
}
