package com.bikram.bookstore.Resources;
import com.bikram.bookstore.domain.Book;
import com.bikram.bookstore.dto.BookDTO;
import com.bikram.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookResource {

    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBooks(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long bookId){
        return bookService.getBookById(bookId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long bookId,
                                                   @RequestBody Book book){

        return bookService.getBookById(bookId)
                .map(savedBook -> {

                    savedBook.setBookName(book.getBookName());
                    savedBook.setBookAuthor(book.getBookAuthor());
                    savedBook.setBookPrice(book.getBookPrice());
                    savedBook.setPurchaseBy(book.getPurchaseBy());
                    savedBook.setPublishedDate(book.getPublishedDate());
                    savedBook.setBookCategory(book.getBookCategory());
                    Book updateBook = bookService.updateBook(savedBook);
                    return new ResponseEntity<>(updateBook, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long BookId){

        bookService.deleteBook(BookId);

        return new ResponseEntity<String>("Book deleted successfully!.", HttpStatus.OK);

    }
}



