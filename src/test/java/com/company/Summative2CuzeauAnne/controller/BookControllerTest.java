package com.company.Summative2CuzeauAnne.controller;

import com.company.Summative2CuzeauAnne.model.Author;
import com.company.Summative2CuzeauAnne.model.Book;
import com.company.Summative2CuzeauAnne.model.Publisher;
import com.company.Summative2CuzeauAnne.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Book book;
    private String bookJson;
    private List<Book> allBooks = new ArrayList<>();
    private String allBooksJson;

    @Before
    public void setup() throws Exception {
        Author author = new Author();
        author.setFirst_name("Qwerty");
        author.setLast_name("LastTest");
        author.setStreet("Test St.");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("75673");
        author.setPhone("123-400-5000");
        author.setEmail("qwerty@qwerty.com");


        Publisher publisher = new Publisher();
        publisher.setName("Publisher1");
        publisher.setState("NY");
        publisher.setStreet("One NYC st.");
        publisher.setPostalCode("45678");
        publisher.setCity("NYC");
        publisher.setPhone("678-900-0000");
        publisher.setEmail("publisher@qwerty.com");


        book = new Book();
        book.setId(1);
        book.setAuthorId(author.getId());
        book.setPublisherId(publisher.getId());
        book.setTitle("TitleTest");
        book.setIsbn("ibsn12345");
        book.setPrice(new BigDecimal("12.00"));

        bookJson = mapper.writeValueAsString(book);

        Book book2 = new Book();
        book2.setAuthorId(author.getId());
        book2.setPublisherId(publisher.getId());
        book2.setTitle("book2 title");
        book2.setIsbn("book2isbn");
        book2.setPrice(new BigDecimal("21.95"));

        allBooksJson = mapper.writeValueAsString(allBooks);
    }

    @Test
    public void shouldCreateNewBookOnPostRequest() throws Exception {
        Book inputRsvp = new Book();
        inputRsvp.setId(1);
        inputRsvp.setAuthorId(1);
        inputRsvp.setPublisherId(2);
        inputRsvp.setTitle("book2 title");
        inputRsvp.setIsbn("book2isbn");
        inputRsvp.setPrice(new BigDecimal("21.95"));
        String inputJson = mapper.writeValueAsString(inputRsvp);

        doReturn(book).when(repo).save(inputRsvp);

        mockMvc.perform(
                        post("/books")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(bookJson));
    }

    @Test
    public void shouldReturnBookById() throws Exception {
        doReturn(Optional.of(book)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/books/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(bookJson))
                );
    }

    @Test
    public void shouldBStatusOkForNonExistentRsvpId() throws Exception {
        doReturn(Optional.empty()).when(repo).findById(35);

        mockMvc.perform(
                        get("/books/35"))
                .andExpect(status().isOk()
                );

    }

    @Test
    public void shouldReturnAllBooks() throws Exception {
        doReturn(allBooks).when(repo).findAll();

        mockMvc.perform(
                        get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(allBooksJson)
                );
    }

    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(
                        put("/books")
                                .content(bookJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/books/1")).andExpect(status().isNoContent());
    }


    @Test
    public void shouldReturnAllBooksByAuthorId() throws Exception {
        Book inputBook = new Book();
        inputBook.setId(1);
        inputBook.setAuthorId(1);
        inputBook.setPublisherId(2);
        inputBook.setTitle("book2 title");
        inputBook.setIsbn("book2isbn");
        inputBook.setPrice(new BigDecimal("21.95"));
        String inputJson = mapper.writeValueAsString(inputBook);

        doReturn(inputBook).when(repo).save(inputBook);

        bookJson = mapper.writeValueAsString(inputBook);

        mockMvc.perform(
                        post("/books")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(bookJson));

        mockMvc.perform(
                        get("/books/author/1")
                                .content(bookJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
