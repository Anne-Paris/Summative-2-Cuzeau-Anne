package com.company.Summative2CuzeauAnne.controller;

import com.company.Summative2CuzeauAnne.model.Book;
import com.company.Summative2CuzeauAnne.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class BookController {
    @Autowired
    BookRepository repository;


    @GetMapping("/books")
    public List<Book> getBooks() {
        return repository.findAll();
    }


    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) {

        Optional<Book> returnVal = repository.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        return repository.save(book);
    }

    @PutMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book) {
        repository.save(book);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id) {
        repository.deleteById(id);
    }

}
