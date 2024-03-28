package com.example.quotely.demo.Entity;

import com.example.quotely.demo.Enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    private  String content;
    private  String quotesAuthor;
    private String category;
    private Long categoryId;
    private Date createdAt;
    private Date updatedAt;
    private Long favouriteCount;
    private GeneralStatus status;

}
