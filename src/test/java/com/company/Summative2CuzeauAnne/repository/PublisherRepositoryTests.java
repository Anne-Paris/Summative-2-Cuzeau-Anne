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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherRepositoryTests {
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
    public void addGetDeletePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Publisher1");
        publisher.setState("NY");
        publisher.setStreet("One NYC st.");
        publisher.setPostalCode("45678");
        publisher.setCity("NYC");
        publisher.setPhone("678-900-0000");
        publisher.setEmail("publisher@qwerty.com");

        publisher = publisherRepository.save(publisher);

        Optional<Publisher> publisher1 = publisherRepository.findById(publisher.getId());

        assertEquals(publisher1.get(), publisher);

        publisherRepository.deleteById(publisher.getId());

        publisher1 = publisherRepository.findById(publisher.getId());

        assertFalse(publisher1.isPresent());
    }

    @Test
    public void updatePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Publisher1");
        publisher.setState("NY");
        publisher.setStreet("One NYC st.");
        publisher.setPostalCode("45678");
        publisher.setCity("NYC");
        publisher.setPhone("678-900-0000");
        publisher.setEmail("publisher@qwerty.com");

        publisher = publisherRepository.save(publisher);

        publisher.setName("Publisher1");
        publisher.setState("CA");
        publisher.setCity("Los Angeles");
        Optional<Publisher> publisher1 = publisherRepository.findById(publisher.getId());

    }


    @Test
    public void getAllPublishers() {

        Publisher publisher = new Publisher();
        publisher.setName("Publisher1");
        publisher.setState("NY");
        publisher.setStreet("One NYC st.");
        publisher.setPostalCode("45678");
        publisher.setCity("NYC");
        publisher.setPhone("678-900-0000");
        publisher.setEmail("publisher@qwerty.com");

        Publisher publisher2 = new Publisher();
        publisher2.setName("Publisher2");
        publisher2.setState("CA");
        publisher2.setStreet("One CA st.");
        publisher2.setPostalCode("00001");
        publisher2.setCity("LA");
        publisher2.setPhone("123-444-0000");
        publisher2.setEmail("publisher2@qwerty.com");

        publisher = publisherRepository.save(publisher);
        publisher2 = publisherRepository.save(publisher2);
        List<Publisher> publisherList = publisherRepository.findAll();

        assertEquals(2, publisherList.size());
        assertTrue(publisherList.contains(publisher));
        assertTrue(publisherList.contains(publisher2));

    }

}
