package com.example.quotely.demo.Entity;

import com.example.quotely.demo.Enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ThemesVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long themesId;
    private String themeName;

}
