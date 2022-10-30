package com.company.Summative2CuzeauAnne.repository;

import com.company.Summative2CuzeauAnne.model.Author;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorRepositoryTests {
    @Autowired
    AuthorRepository authorRepository;

    @Before
    public void setUp() throws Exception {
        authorRepository.deleteAll();
    }

    @Test
    public void addGetDeleteAuthor() {

        Author author = new Author();
        author.setFirst_name("Qwerty");
        author.setLast_name("LastTest");
        author.setStreet("Test St.");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("75673");
        author.setPhone("123-400-5000");
        author.setEmail("qwerty@qwerty.com");

        author = authorRepository.save(author);

        Optional<Author> author1 = authorRepository.findById(author.getId());

        assertEquals(author1.get(), author);

        authorRepository.deleteById(author.getId());

        author1 = authorRepository.findById(author.getId());

        assertFalse(author1.isPresent());

    }

    @Test
    public void updateAuthor() {

        Author author = new Author();
        author.setFirst_name("Qwerty");
        author.setLast_name("LastTest");
        author.setStreet("Test St.");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("75673");
        author.setPhone("123-400-5000");
        author.setEmail("qwerty@qwerty.com");

        author = authorRepository.save(author);

        Optional<Author> author1 = authorRepository.findById(author.getId());

        author.setStreet("Update St.");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("75673");

        authorRepository.save(author);

        Optional<Author> author2 = authorRepository.findById(author.getId());

        assertFalse(author1.get().equals(author2));

        assertEquals(author2.get(), author);

        authorRepository.deleteAll();
    }

    @Test
    public void getAllAuthors() {

        Author author = new Author();
        author.setFirst_name("Qwerty");
        author.setLast_name("LastTest");
        author.setStreet("Test St.");
        author.setCity("Austin");
        author.setState("TX");
        author.setPostalCode("75673");
        author.setPhone("123-400-5000");
        author.setEmail("qwerty@qwerty.com");

        author = authorRepository.save(author);

        Author author2 = new Author();
        author2.setFirst_name("SecondTest");
        author2.setLast_name("Test2");
        author2.setStreet("Test2 St.");
        author2.setCity("NYC");
        author2.setState("NY");
        author2.setPostalCode("01234");
        author2.setPhone("234-400-5000");
        author2.setEmail("second@qwerty.com");

        author2 = authorRepository.save(author2);

        List<Author> authorsList = authorRepository.findAll();
        assertEquals(authorsList.size(), 2);
        assertTrue(authorsList.contains(author2));
        assertTrue(authorsList.contains(author));

        authorRepository.deleteAll();
    }

}
