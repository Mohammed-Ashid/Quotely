package com.example.quotely.demo.Vo;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseData {
    public String code;
    public String status;
    public String message;
    public Optional<List<Data>> data;
}


