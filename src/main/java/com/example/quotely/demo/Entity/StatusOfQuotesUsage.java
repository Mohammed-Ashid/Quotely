package com.example.quotely.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name="StatusOfQuotesUsage")
@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor

public class StatusOfQuotesUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;
    private Long userId;
    private Long quotesId;
}
