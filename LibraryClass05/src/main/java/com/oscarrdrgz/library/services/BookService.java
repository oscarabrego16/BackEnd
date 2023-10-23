package com.oscarrdrgz.library.services;

import java.util.List;

import com.oscarrdrgz.library.models.entities.Book;
import com.oscarrdrgz.library.services.utils.ServiceResponse;

//controlara la entidad de negocio Book
public interface BookService {
	//reglas de negocio
	ServiceResponse<Void> insert(Book book);
	ServiceResponse<Void>  delete(String isbn);
	ServiceResponse<Book>  getOneById(String isbn);
	ServiceResponse<List<Book>>  getAll();
	ServiceResponse<List<String>>  getAllIsbn();
}
