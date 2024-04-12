package com.example.quotely.demo.Repository;

import com.example.quotely.demo.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {


    @Query("SELECT s.numberOfQuotes FROM Categories s WHERE s.categoriesId = :categoriesId ")
    Long findNumberOfQuotesByCategoriesId(@Param(("categoriesId")) Long categoriesId);
}
