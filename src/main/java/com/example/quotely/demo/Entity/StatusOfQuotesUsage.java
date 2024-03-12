package com.example.quotely.demo.Entity;

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
    private Long userId;
    private Long quotesId;
}
