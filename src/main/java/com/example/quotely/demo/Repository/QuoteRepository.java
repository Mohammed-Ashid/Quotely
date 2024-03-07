package com.example.quotely.demo.Repository;

import com.example.quotely.demo.Entity.Quotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quotes,Long> {
}
