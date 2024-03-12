package com.example.quotely.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name="Users")
@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
}
