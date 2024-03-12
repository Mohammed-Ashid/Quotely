package com.example.quotely.demo.Vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Data {
    private long id;
    private String category;
    private Object dataList;

    // Constructors, getters, and setters
    // ...

//    @Override
//    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//
//        if (dataList != null) {
//            for (int i = 0; i < dataList.size(); i++) {
//                T data = dataList.get(i);
//                stringBuilder.append("id=").append(i + 1)
//                        .append(", category='").append(category).append("', data=").append(data);
//
//                if (i < dataList.size() - 1) {
//                    stringBuilder.append(System.lineSeparator());
//                }
//            }
//        }
//
//        return stringBuilder.toString();
//    }
}
