package com.company.Summative2CuzeauAnne.controller;

import com.company.Summative2CuzeauAnne.model.Author;
import com.company.Summative2CuzeauAnne.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {
    @Autowired
    AuthorRepository repository;

    @GetMapping("/authors")
    public List<Author> getAuthors() {
        return repository.findAll();
    }


    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable int id) {
        Optional<Author> returnVal = repository.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody Author author) {
        return repository.save(author);
    }

    @PutMapping("/authors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthor(@RequestBody Author artist) {
        repository.save(artist);
    }

    @DeleteMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int id) {
        repository.deleteById(id);
    }

}
