package com.oscarrdrgz.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscarrdrgz.library.models.entities.Book;

//encargado de interactuar con Book
public interface BookRepository extends JpaRepository<Book, String>{

}
