package com.codetech.book.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.codetech.book.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
