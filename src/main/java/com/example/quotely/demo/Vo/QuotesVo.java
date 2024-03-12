package com.example.quotely.demo.Vo;

import jakarta.persistence.*;
import lombok.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Table
@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter

public class QuotesVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quotesId;
    private String category;
    private  String content;

}
