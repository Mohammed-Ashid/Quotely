package com.example.quotely.demo.Vo;

import jakarta.persistence.*;
import lombok.*;
import lombok.Data;

@Table
@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter

public class UsersVo {


    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
