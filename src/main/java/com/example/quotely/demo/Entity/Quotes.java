package com.example.quotely.demo.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name="Quotes")
@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor

public class Quotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quotesId;
    private String category;
    private  String content;

}
