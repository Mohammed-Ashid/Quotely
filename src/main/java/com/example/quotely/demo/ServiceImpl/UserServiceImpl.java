package com.example.quotely.demo.ServiceImpl;

import com.example.quotely.demo.Entity.Users;
import com.example.quotely.demo.Mapper.UserMapper;
import com.example.quotely.demo.Repository.UserRepository;
import com.example.quotely.demo.Service.UserService;
import com.example.quotely.demo.Vo.Data;
import com.example.quotely.demo.Vo.ResponseData;
import com.example.quotely.demo.Vo.UsersVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public ResponseData addUser(UsersVo userVo) {
        Users user= UserMapper.toUser(userVo);
        userRepository.save(user);
        Data dataResult = Data.builder()
                .id(user.getUserId())
                .category("user")
                .dataList("User Registered successfully")
                .build();
        ResponseData responseData= ResponseData.builder()
                .code("success")
                .status("success")
                .message("New User Registered")
                .data(Optional.ofNullable(Collections.singletonList(dataResult)))
                .build();
        return responseData;
    }
}
