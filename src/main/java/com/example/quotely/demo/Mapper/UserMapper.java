package com.example.quotely.demo.Mapper;

import com.example.quotely.demo.Entity.Users;
import com.example.quotely.demo.Vo.UsersVo;

public class UserMapper {
    public static Users toUser(UsersVo userVo){
        return Users.builder()
                .firstName(userVo.getFirstName())
                .lastName(userVo.getLastName())
                .build();
    }
}
