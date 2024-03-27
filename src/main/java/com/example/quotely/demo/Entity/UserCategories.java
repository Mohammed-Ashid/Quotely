package com.example.quotely.demo.Entity;

import com.example.quotely.demo.Enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.*;

@Table(name="UserCategories")
@Entity
@Data

@Builder

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categorySlno;
    private Long categoriesId;
    private Long usersId;
    private GeneralStatus status;
}
