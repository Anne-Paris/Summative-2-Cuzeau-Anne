package com.company.Summative2CuzeauAnne.repository;

import com.company.Summative2CuzeauAnne.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
