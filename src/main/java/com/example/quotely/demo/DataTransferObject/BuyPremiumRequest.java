package com.example.quotely.demo.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Getter
@Setter
public class BuyPremiumRequest {
    private Long userId;
    private Long duration;
    private String authKey;
}
