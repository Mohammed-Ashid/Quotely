package com.example.quotely.demo.Vo;

import com.example.quotely.demo.Enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Table
@Entity
@Data

@Builder

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCategoriesVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categorySlno;
    private Optional<List<Long>> categoriesId;
    private Long usersId;
    private GeneralStatus status;
}

