package com.example.quotely.demo.Repository;

import com.example.quotely.demo.Entity.StatusOfQuotesUsage;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface StatusOfQuotesUsageRepository extends JpaRepository<StatusOfQuotesUsage,Long> {
    @Query("SELECT s.userId FROM StatusOfQuotesUsage s WHERE s.quotesId = :quotesId")
    Optional<List<Long>> findUserIdByQuotesId(@Param("quotesId") Long quotesId);


}
