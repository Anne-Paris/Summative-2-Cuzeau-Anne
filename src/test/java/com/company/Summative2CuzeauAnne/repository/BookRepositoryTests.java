package com.company.Summative2CuzeauAnne.repository;

import com.company.Summative2CuzeauAnne.model.Author;
import com.company.Summative2CuzeauAnne.model.Book;
import com.company.Summative2CuzeauAnne.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookRepositoryTests {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @Before
    public void setUp() throws Exception {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();
    }

    @Test
    public void addGetDeleteBook() {

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

        Publisher publisher = new Publisher();
        publisher.setName("Publisher1");
        publisher.setState("NY");
        publisher.setStreet("One NYC st.");
        publisher.setPostalCode("45678");
        publisher.setCity("NYC");
        publisher.setPhone("678-900-0000");
        publisher.setEmail("publisher@qwerty.com");

        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setAuthorId(author.getId());
        book.setPublisherId(publisher.getId());
        book.setTitle("TitleTest");
        book.setIsbn("ibsn12345");
        book.setPublish_date(LocalDate.of(2021, 9, 9));
        book.setPrice(new BigDecimal("12.00"));

        book = bookRepository.save(book);
        Optional<Book> book1 = bookRepository.findById(book.getId());

        assertEquals(book1.get(), book);

        bookRepository.deleteById(book.getId());

        book1 = bookRepository.findById(book.getId());

        assertFalse(book1.isPresent());
    }

    @Test
    public void updateBook() {

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

        Publisher publisher = new Publisher();
        publisher.setName("Publisher1");
        publisher.setState("NY");
        publisher.setStreet("One NYC st.");
        publisher.setPostalCode("45678");
        publisher.setCity("NYC");
        publisher.setPhone("678-900-0000");
        publisher.setEmail("publisher@qwerty.com");

        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setAuthorId(author.getId());
        book.setPublisherId(publisher.getId());
        book.setTitle("TitleTest");
        book.setIsbn("ibsn12345");
        book.setPublish_date(LocalDate.of(2021, 9, 9));
        book.setPrice(new BigDecimal("12.00"));
        book = bookRepository.save(book);

        Optional<Book> book1 = bookRepository.findById(book.getId());
        book.setTitle("TitleUpdate");
        book.setIsbn("updatedisbn");

        bookRepository.save(book);

        Optional<Book> book2 = bookRepository.findById(book.getId());

        assertFalse(book1.get().equals(book2));

        assertEquals(book2.get(), book);
    }

    @Test
    public void getAllBooks() {

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

        Publisher publisher = new Publisher();
        publisher.setName("Publisher1");
        publisher.setState("NY");
        publisher.setStreet("One NYC st.");
        publisher.setPostalCode("45678");
        publisher.setCity("NYC");
        publisher.setPhone("678-900-0000");
        publisher.setEmail("publisher@qwerty.com");

        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setAuthorId(author.getId());
        book.setPublisherId(publisher.getId());
        book.setTitle("TitleTest");
        book.setIsbn("ibsn12345");
        book.setPublish_date(LocalDate.of(2021, 9, 9));
        book.setPrice(new BigDecimal("12.00"));

        book = bookRepository.save(book);

        Book book2 = new Book();
        book2.setAuthorId(author.getId());
        book2.setPublisherId(publisher.getId());
        book2.setTitle("book2 title");
        book2.setIsbn("book2isbn");
        book2.setPublish_date(LocalDate.of(1999, 9, 9));
        book2.setPrice(new BigDecimal("21.95"));

        book2 = bookRepository.save(book2);

        List<Book> bookList = bookRepository.findAll();

        assertEquals(bookList.size(), 2);
    }

    @Test
    public void getBooksByAuthorId() {
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

        Publisher publisher = new Publisher();
        publisher.setName("Publisher1");
        publisher.setState("NY");
        publisher.setStreet("One NYC st.");
        publisher.setPostalCode("45678");
        publisher.setCity("NYC");
        publisher.setPhone("678-900-0000");
        publisher.setEmail("publisher@qwerty.com");

        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setAuthorId(author.getId());
        book.setPublisherId(publisher.getId());
        book.setTitle("TitleTest");
        book.setIsbn("ibsn12345");
        book.setPublish_date(LocalDate.of(2021, 9, 9));
        book.setPrice(new BigDecimal("12.00"));

        book = bookRepository.save(book);

        Book book2 = new Book();
        book2.setAuthorId(author.getId());
        book2.setPublisherId(publisher.getId());
        book2.setTitle("book2 title");
        book2.setIsbn("book2isbn");
        book2.setPublish_date(LocalDate.of(1999, 9, 9));
        book2.setPrice(new BigDecimal("21.95"));

        book2 = bookRepository.save(book2);


        Author author2 = new Author();
        author2.setFirst_name("Second");
        author2.setLast_name("Author");
        author2.setStreet("Blabla St.");
        author2.setCity("Los Angeles");
        author2.setState("CA");
        author2.setPostalCode("45000");
        author2.setPhone("222-400-5000");
        author2.setEmail("second@qwerty.com");

        author2 = authorRepository.save(author2);

        Book book3 = new Book();
        book3.setAuthorId(author2.getId());
        book3.setPublisherId(publisher.getId());
        book3.setTitle("book3");
        book3.setIsbn("book3isbn");
        book3.setPublish_date(LocalDate.of(1840, 9, 9));
        book3.setPrice(new BigDecimal("45.95"));

        book3 = bookRepository.save(book3);

        Integer authorID = author.getId();
        List<Book> bookList = bookRepository.findAllBooksByAuthorId(authorID);

        assertEquals(bookList.size(), 2);

        assertTrue(bookList.contains(book));
        assertTrue(bookList.contains(book2));
    }


}
