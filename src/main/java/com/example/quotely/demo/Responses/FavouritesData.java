package com.example.quotely.demo.Responses;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavouritesData {
    public Long quotesId;
    public Long userId;
}
