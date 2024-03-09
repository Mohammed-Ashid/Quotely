package com.example.quotely.demo.Vo;

import com.example.quotely.demo.Vo.Data;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public List<Data> data;
}


