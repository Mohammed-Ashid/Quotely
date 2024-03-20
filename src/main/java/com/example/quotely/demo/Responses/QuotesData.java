package com.example.quotely.demo.Responses;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuotesData {
    private long id;
    private String category;
    private Object dataList;


}
