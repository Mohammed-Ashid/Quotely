package com.example.quotely.demo.Entity;

import com.example.quotely.demo.Enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categories {
    @Id

    private long categoriesId;
    private String categoriesName;
    private Date createdAt;
    private Long numberOfQuotes;
    private GeneralStatus status;

}
