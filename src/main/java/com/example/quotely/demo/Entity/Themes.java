package com.example.quotely.demo.Entity;

import com.example.quotely.demo.Enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.*;

@Table(name="Themes")
@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Themes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long themesId;
    private String themesName;
    private GeneralStatus status;
}
