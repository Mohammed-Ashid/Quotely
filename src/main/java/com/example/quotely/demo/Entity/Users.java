package com.example.quotely.demo.Entity;

import com.example.quotely.demo.Enums.GeneralStatus;
import com.example.quotely.demo.Enums.PremiumStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name="Users")
@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
//    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    private Long usersId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date createdAt;
    private Date updatedAt;
    private String authKey;
    private Long themesId;
    private PremiumStatus premiumStatus;
    private Date premiumStartDate;
    private Date premiumEndDate;
    private GeneralStatus status;
}
