package com.example.quotely.demo.Vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.Data;

@Table
@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter

public class UserVo {
    @Id
    private Long userId;
    private String firstName;
    private String Lastname;
}
