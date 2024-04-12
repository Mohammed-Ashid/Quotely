package com.example.quotely.demo.Repository;

import com.example.quotely.demo.Entity.UserCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserCategoriesRepository extends JpaRepository<UserCategories,Long> {

    @Modifying
    @Query("DELETE FROM UserCategories c WHERE c.usersId = :userId")
    void deleteByUserId(@Param("userId") Long userId);
    @Query("SELECT s.categoriesId FROM UserCategories s WHERE s.authKey = :authKey")
    Optional<List<Long>> findByAuthKey(@Param("authKey") String authKey);

}
