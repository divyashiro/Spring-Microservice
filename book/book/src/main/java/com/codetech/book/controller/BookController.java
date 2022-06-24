package com.codetech.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetech.book.model.Book;
import com.codetech.book.repository.BookRepository;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@PostMapping
	public Optional<Book> postBook(@RequestBody Book book) {
		bookRepository.insert(book);
		return bookRepository.findById(book.getId());
	}
	
	@GetMapping
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public Optional<Book> getBookById(@PathVariable String id) {
		return bookRepository.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable String id) {
		if(bookRepository.existsById(id))
		     bookRepository.deleteById(id);
		else
			return "Book not found";
		return "Deleted";
		
		
	}
	
	
	

}
