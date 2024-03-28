package com.example.quotely.demo.Vo;

import com.example.quotely.demo.Enums.GeneralStatus;
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

public class QuotesVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quotesId;
    private  String content;
    private  String quotesAuthor;
    private String category;
    private Long categoryId;

}
