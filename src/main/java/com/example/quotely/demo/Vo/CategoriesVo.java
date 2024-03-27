package com.example.quotely.demo.Vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Table
@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter

public class CategoriesVo {
    @Id

    private long categoriesId;
    private String categoriesName;
    private Date createdAt;
}
