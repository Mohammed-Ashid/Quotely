package com.example.quotely.demo.Repository;

import com.example.quotely.demo.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {
}
