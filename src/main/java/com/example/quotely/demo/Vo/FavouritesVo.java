package com.example.quotely.demo.Vo;

import com.example.quotely.demo.Enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Data

@Builder

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavouritesVo {

    private Long usersId;
    private Long quotesId;

}
