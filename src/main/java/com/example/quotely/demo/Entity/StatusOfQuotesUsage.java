package com.example.quotely.demo.Entity;

import com.example.quotely.demo.Enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.*;

@Table(name="StatusOfQuotesUsage")
@Entity
@Data

@Builder

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StatusOfQuotesUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;
    private Long usersId;
    private Long quotesId;
    private Long quotesCategoryId;
    private String authKey;
    private GeneralStatus status;
}
