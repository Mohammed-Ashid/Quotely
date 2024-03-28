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
public class UpdateUserRequest {
    private Long usersId;
    private String oldPassword;
    private String newPassword;
    private String authkey;
}
