package com.example.quotely.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name="StatusOfQuotesUsage")
@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor

public class SatusOfQuotesUsage {
    @Id
    private Long statusId;
    private Long userId;
    private Long quotesId;
}
