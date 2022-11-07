package com.bikram.bookstore.Resources;

import com.bikram.bookstore.domain.Book;
import com.bikram.bookstore.domain.BookCategory;
import com.bikram.bookstore.dto.BookDTO;
import com.bikram.bookstore.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class BookResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenBookObject_whenCreateBook_thenReturnSavedBook() throws Exception {
        // given - precondition or setup
        BookCategory bookCategory = BookCategory.builder()
                .bookBranch("Science")
                .bookSubBranch("Physics").build();
        Book book = Book.builder()
                .bookName("LOD")
                .bookCategory(bookCategory)
                .bookAuthor("Marcus")
                .publishedDate(LocalDate.parse("2022-11-12"))
                .bookPrice(800)
                .purchaseBy("john")
                .build();
        given(bookService.saveBook(any(Book.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookName",
                        is(book.getBookName())))
                .andExpect(jsonPath("$.bookAuthor",
                        is(book.getBookAuthor())))
                .andExpect(jsonPath("$.bookPrice",
                        is(book.getBookPrice())));

    }

    // JUnit test for Get All Books REST API
    @Test
    public void givenListOfBooks_whenGetAllBooks_thenReturnBooksList() throws Exception {
        // given - precondition or setup
        List<Book> listOfBooks = new ArrayList<>();
        listOfBooks.add(Book.builder().bookName("LOD").bookAuthor("Fadatare").bookPrice(800).purchaseBy("John").build());
        listOfBooks.add(Book.builder().bookName("Tony").bookAuthor("Stark").bookPrice(900).purchaseBy("smith").build());
        given(bookService.getAllBooks()).willReturn(listOfBooks);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/books"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfBooks.size())));

    }

    // positive scenario - valid Book id
    // JUnit test for GET Book by id REST API
    @Test
    public void givenBookId_whenGetBookById_thenReturnBookObject() throws Exception {
        // given - precondition or setup
        long bookId = 1L;
        Book book = Book.builder()
                .bookName("LOD")
                .bookAuthor("Marcus")
                .bookPrice(800)
                .build();
        given(bookService.getBookById(bookId)).willReturn(Optional.of(book));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/books/{id}", bookId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.bookName", is(book.getBookName())))
                .andExpect(jsonPath("$.bookAuthor", is(book.getBookAuthor())))
                .andExpect(jsonPath("$.bookPrice", is(book.getBookPrice())));

    }

    // negative scenario - valid Book id
    // JUnit test for GET Book by id REST API
    @Test
    public void givenInvalidBookId_whenGetBookById_thenReturnEmpty() throws Exception {
        // given - precondition or setup
        long bookId = 1L;
        Book book = Book.builder()
                .bookName("Ramesh")
                .bookAuthor("Fadatare")
                .bookPrice(800)
                .build();
        given(bookService.getBookById(bookId)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/books/{id}", bookId));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());

    }

    // JUnit test for update Book REST API - positive scenario
    @Test
    public void givenUpdatedBook_whenUpdateBook_thenReturnUpdateBookObject() throws Exception {
        // given - precondition or setup
        long bookId = 1L;
        BookCategory bookCategory =BookCategory.builder()
                .bookSubBranch("Physics")
                .bookBranch("Science").build();
        Book savedBook = Book.builder()
                .bookName("LOD")
                .bookAuthor("Marcus")
                .bookPrice(400)
                .bookCategory(bookCategory)
                .build();

        BookCategory updatedBookCategory =BookCategory.builder()
                .bookSubBranch("Geology")
                .bookBranch("Science").build();
        Book updatedBook = Book.builder()
                .bookName("Bell")
                .bookAuthor("Peter")
                .bookPrice(800)
                .bookCategory(updatedBookCategory)
                .build();
        given(bookService.getBookById(bookId)).willReturn(Optional.of(savedBook));
        given(bookService.updateBook(any(Book.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/books/{id}", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)));


        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.bookName", is(updatedBook.getBookName())))
                .andExpect(jsonPath("$.bookAuthor", is(updatedBook.getBookAuthor())))
                .andExpect(jsonPath("$.bookPrice", is(updatedBook.getBookPrice())));
    }

    // JUnit test for update Book REST API - negative scenario
//  

    // JUnit test for delete Book REST API
    @Test
    public void givenBookId_whenDeleteBook_thenReturn200() throws Exception {
        // given - precondition or setup
        long bookId = 1L;
        willDoNothing().given(bookService).deleteBook(bookId);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/api/books/{id}", bookId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
