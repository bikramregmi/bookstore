package com.bikram.bookstore;
import com.bikram.bookstore.domain.BookCategory;
import com.bikram.bookstore.exceptions.ResourceNotFound;
import com.bikram.bookstore.domain.Book;
import com.bikram.bookstore.repository.BookRepository;
import com.bikram.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        Optional<Book> savedBook= bookRepository.findById(book.getId());
        if(savedBook.isPresent()){
            throw new ResourceNotFound("Book already exist with given name:" + book.getBookName());
        }
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book updateBook(Book updatedBook) {
        return bookRepository.save(updatedBook);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
