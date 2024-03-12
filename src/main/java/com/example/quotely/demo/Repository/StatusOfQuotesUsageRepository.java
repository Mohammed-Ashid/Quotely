package com.example.quotely.demo.Repository;

import com.example.quotely.demo.Entity.StatusOfQuotesUsage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface StatusOfQuotesUsageRepository extends JpaRepository<StatusOfQuotesUsage,Long> {
    @Query("SELECT s.userId FROM StatusOfQuotesUsage s WHERE s.quotesId = :quotesId")
    Optional<Long> findUserIdByQuotesId(@Param("quotesId") Long quotesId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO StatusOfQuotesUsage (user_id, quotes_id) VALUES (:userId, :quotesId)", nativeQuery = true)
    void saveUserIdAndQuotesId(Long userId, Long quotesId);
}
