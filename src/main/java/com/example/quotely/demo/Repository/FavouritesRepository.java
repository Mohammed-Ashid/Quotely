package com.example.quotely.demo.Repository;

import com.example.quotely.demo.Entity.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavouritesRepository extends JpaRepository<Favourites,Long> {
    @Query("SELECT s.quotesId FROM Favourites s WHERE s.usersId = :userId")
    Optional<List<Long>> findQuotesByUserId(@Param("userId") Long userId);
    @Query("DELETE FROM Favourites f WHERE f.usersId = :userId AND f.quotesId = :quotesId")
    void deleteByUserIdAndQuotesId(@Param("userId") Long userId, @Param("quotesId") Long quotesId);





    @Query("SELECT f FROM Favourites f WHERE f.usersId = :userId AND f.quotesId = :quotesId")
    Optional<Favourites> findByUserIdAndQuotesId(@Param("userId") Long userId, @Param("quotesId") Long quotesId);
}
