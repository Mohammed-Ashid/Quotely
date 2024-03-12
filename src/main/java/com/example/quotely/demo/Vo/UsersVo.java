package com.example.quotely.demo.Vo;

import jakarta.persistence.*;
import lombok.*;
import lombok.Data;

@Table
@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter

public class UsersVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastname;
}
