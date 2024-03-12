package com.example.quotely.demo.Repository;

import com.example.quotely.demo.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
