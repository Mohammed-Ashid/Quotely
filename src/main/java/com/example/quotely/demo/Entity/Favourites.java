package com.example.quotely.demo.Entity;

import com.example.quotely.demo.Enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.*;

@Table(name="Favourites")
@Entity
@Data

@Builder

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favouritesId;
    private Long usersId;
    private Long quotesId;
    private GeneralStatus status;
}
