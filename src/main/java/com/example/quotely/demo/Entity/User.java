package com.example.quotely.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name="User")
@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private Long userId;
    private String firstName;
    private String Lastname;
}
