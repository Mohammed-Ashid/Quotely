package com.example.quotely.demo.Vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Generated
    private Long id;
    private String category;
    private  String content;

}
