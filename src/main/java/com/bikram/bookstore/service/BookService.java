package com.bikram.bookstore.service;

import com.bikram.bookstore.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    Book saveBook(Book book);
    List<Book> getAllBooks();
    Optional<Book> getBookById(long id);
    Book updateBook(Book updatedBook);
    void deleteBook(long id);
}
