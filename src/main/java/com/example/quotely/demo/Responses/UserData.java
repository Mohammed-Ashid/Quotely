package com.example.quotely.demo.Responses;

import com.example.quotely.demo.Enums.GeneralStatus;
import com.example.quotely.demo.Enums.PremiumStatus;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserData {
    private Long id;
    private String type;
    private String name;
    private Date createdAt;
    private Date lastUpdated;
    private  Long themeId;
    private String authKey;
    private GeneralStatus status;
    private PremiumStatus premiumStatus;
    private LocalDate premiumEndDate;
    private String response;
}
