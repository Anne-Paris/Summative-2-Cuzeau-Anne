package com.company.Summative2CuzeauAnne.controller;

import com.company.Summative2CuzeauAnne.model.Author;
import com.company.Summative2CuzeauAnne.model.Book;
import com.company.Summative2CuzeauAnne.repository.AuthorRepository;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Author author;
    private String authorJson;
    private List<Author> allAuthor = new ArrayList<>();
    private String allAuthorJson;

    @Before
    public void setup() throws Exception {
        author = new Author();
        author.setId(1);
        author.setFirst_name("Qwerty");
        author.setLast_name("LastTest");
        author.setStreet("Test St.");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("75673");
        author.setPhone("123-400-5000");
        author.setEmail("qwerty@qwerty.com");

        authorJson = mapper.writeValueAsString(author);

        Author author2 = new Author();
        author2.setId(2);
        author2.setFirst_name("SecondTest");
        author2.setLast_name("Test2");
        author2.setStreet("Test2 St.");
        author2.setCity("NYC");
        author2.setState("NY");
        author2.setPostalCode("01234");
        author2.setPhone("234-400-5000");
        author2.setEmail("second@qwerty.com");

        allAuthor.add(author);
        allAuthor.add(author2);

        allAuthorJson = mapper.writeValueAsString(allAuthor);

    }

    @Test
    public void shouldCreateNewAuthorOnPostRequest() throws Exception {
        Author inputAuthor = new Author();
        inputAuthor.setId(1);
        inputAuthor.setFirst_name("Qwerty");
        inputAuthor.setLast_name("LastTest");
        inputAuthor.setStreet("Test St.");
        inputAuthor.setCity("Austin");
        inputAuthor.setState("TX");
        inputAuthor.setPostalCode("75673");
        inputAuthor.setPhone("123-400-5000");
        inputAuthor.setEmail("qwerty@qwerty.com");
        String inputJson = mapper.writeValueAsString(inputAuthor);

        doReturn(author).when(repo).save(inputAuthor);

        mockMvc.perform(
                        post("/authors")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldReturnAllAuthor() throws Exception {
        doReturn(allAuthor).when(repo).findAll();

        mockMvc.perform(get("/authors"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAuthorById() throws Exception {
        Author author = new Author();
        author.setId(1);
        author.setFirst_name("Qwerty");
        author.setLast_name("LastTest");
        author.setStreet("Test St.");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("75673");
        author.setPhone("123-400-5000");
        author.setEmail("qwerty@qwerty.com");
        doReturn(Optional.of(author)).when(repo).findById(1);

        mockMvc.perform(get("/authors/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {

        Author author = new Author();
        author.setId(1);
        author.setFirst_name("Qwerty");
        author.setLast_name("LastTest");
        author.setStreet("Test St.");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("75673");
        author.setPhone("123-400-5000");
        author.setEmail("qwertUpdatesy@qwerty.com");

        String inputJson = mapper.writeValueAsString(author);
        mockMvc.perform(
                        put("/authors")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/authors/2")).andExpect(status().isNoContent());
    }

    @Test
    public void shouldBStatusOkForNonExistentAuthorId() throws Exception {
        doReturn(Optional.empty()).when(repo).findById(25);

        mockMvc.perform(
                        get("/authors/25"))
                .andExpect(status().isOk()
                );

    }

}
