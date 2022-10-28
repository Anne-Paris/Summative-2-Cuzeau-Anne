package com.company.Summative2CuzeauAnne.repository;

import com.company.Summative2CuzeauAnne.model.Author;
import com.company.Summative2CuzeauAnne.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository  extends JpaRepository<Author, Integer> {
}