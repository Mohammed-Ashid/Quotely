package com.example.quotely.demo.Repository;

import com.example.quotely.demo.Entity.Quotes;
import com.example.quotely.demo.Vo.QuotesVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quotes,Long> {

//    @Query("SELECT q FROM Quote q WHERE q.categoryId IN :userSelectedCategory")
//    List<Quotes> findByCategoryId(@Param("userSelectedCategory") Optional<List<Long>> userSelectedCategory);

}
