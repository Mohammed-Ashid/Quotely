package com.example.quotely.demo.Responses;

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

public class CategoriesData {
    public Long userId;
    public Long categoriesId;
    public String categoriesname;
}
