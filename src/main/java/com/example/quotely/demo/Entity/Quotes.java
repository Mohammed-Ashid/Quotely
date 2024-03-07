package com.example.quotely.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Generated
    private Long id;
    private String category;
    private  String content;

}
