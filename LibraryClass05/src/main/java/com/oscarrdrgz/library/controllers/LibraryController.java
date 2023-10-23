package com.oscarrdrgz.library.controllers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oscarrdrgz.library.models.dtos.BookAddDTO;
import com.oscarrdrgz.library.models.dtos.BookDetailsDTO;
import com.oscarrdrgz.library.models.dtos.BookResponseDTO;
import com.oscarrdrgz.library.models.dtos.BookSearchDTO;
import com.oscarrdrgz.library.models.entities.Book;
import com.oscarrdrgz.library.models.entities.User;
import com.oscarrdrgz.library.services.BookService;
import com.oscarrdrgz.library.services.UserService;
import com.oscarrdrgz.library.services.utils.ServiceResponse;

import jakarta.validation.Valid;


//@RestController permite devolver todo lo de una rest, texto plano, jsons, entre otros
@Controller
//este es como el generico
@RequestMapping("/library")
public class LibraryController {
	
	//Spring, inserta una instancia de ese tipo.
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	
	//@GetMapping("/") es equivalente
	//debe marcarse el objeto search en todas las paginas que devuelvan main y que lo requieran
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String getMainPage(Model model) {
		String time = Calendar.getInstance().getTime().toString();
		//se inyecta el tiempo a partir del modelo a la vista main
		model.addAttribute("time", time);
		//no fue necesario marcarlo puesto que se manda el objeto al main
		model.addAttribute("search", new BookSearchDTO("", ""));
		return "main";
	}
	
	@PostMapping("/book")
	//Model attribute indica que BookSearchDTO search es un atributo que viene en la peticion
	//y por tanto que mapee lo que viene con ese POJO
	
	//BindingResult es un objeto que trae el resultado de la union/transformacion de la peticion al objeto search
	//basicamente hacer las validacione snecesarias
	//NO OLVIDAR NOTACION @VALID
	
	private String requestBookPage(@ModelAttribute(name="search") @Valid BookSearchDTO search, BindingResult result, Model model) {
		String isbn = search.getIsbn();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error->System.out.println(error.getDefaultMessage()));
			return "main";
		}
		String username = search.getPerson();
	User foundUser = userService.getOneById(username);
	if(foundUser ==null) {
		model.addAttribute("error", "Usuario no encontrado");
		return "main";
	}
		
		
		/**
		//en capa de servicio debe filtrarse, de momento aca
		Book foundBook=  library.stream()
			.filter((book)->{ return book.getIsbn().equals(isbn);})
			.findAny()
			.orElse(new Book("","")); //El optional del findAny
		**/
		
		ServiceResponse<Book> foundBookResponse= bookService.getOneById(isbn);
		/**
		List<String> isbns = library.stream()
								.map((book)->{return book.getIsbn();})
								.toList();
		**/
		
		List<String> isbns = bookService.getAllIsbn().getData();
		
		//El servicio no se encarga de controlar si hay errores
		if(!foundBookResponse.getStatus()) {
			model.addAttribute("error","Libro no encontrado");
			return "main";
		}
		
		Book foundBook = foundBookResponse.getData();
		
		BookResponseDTO response = new BookResponseDTO(foundBook.getTitle(), foundUser.getFirstname()+" "+ foundUser.getLastname(), isbns);
		
		//model.addAttribute("title", foundBook.getTitle());
		//model.addAttribute("person",search.getPerson());
		model.addAttribute("book", response);
		
		
		return "book";
	}
	
	@GetMapping("/book/add")
	private String getAddPage(Model model) {
		model.addAttribute("book", new BookAddDTO("",""));
		return "add_book";
	}
	
	
	//En el binding result va a decir si el valid trae error o no 
	@PostMapping("/book/add")
	public String addBook(@ModelAttribute(name="book") @Valid BookAddDTO bookInfo,BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "add_book";
		}
		ServiceResponse<Book>  foundBook = bookService.getOneById(bookInfo.getIsbn());
		if(foundBook.getStatus()) {
			model.addAttribute("error", "El libro ya existe");
			return "add_book";
		}
		ServiceResponse<Void>insertResponse=  bookService.insert(new Book(bookInfo.getIsbn(), bookInfo.getTitle())); 
		if(!insertResponse.getStatus()) {
			model.addAttribute("error", "El guardado ha fallado");
			return "add_book";
		}
		
		
		return "redirect:/library/";
	}
	
	@GetMapping("/book/details/{isbn}")
	public String getBookDetailsPage(@PathVariable(name="isbn") String isbn,Model model) {
		ServiceResponse<Book> foundBookResponse = bookService.getOneById(isbn);
		if(foundBookResponse.getStatus()) {
			//no existe el libro
			return "redirect:/library/";
		}
		
		Book foundBook = foundBookResponse.getData();
		
		BookDetailsDTO response= new BookDetailsDTO(foundBook.getTitle());
		model.addAttribute("book", response);
		
		
		
		return "book_details";
	}

}
