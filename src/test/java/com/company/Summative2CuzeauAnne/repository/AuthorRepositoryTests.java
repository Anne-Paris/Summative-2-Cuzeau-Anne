package com.company.Summative2CuzeauAnne.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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


    }
}
