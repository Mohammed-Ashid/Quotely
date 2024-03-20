package com.example.quotely.demo.Service;

import com.example.quotely.demo.Responses.QuotesResponseData;
import com.example.quotely.demo.Responses.UserResponseData;
import com.example.quotely.demo.Vo.UsersVo;

public interface UserService {
    public UserResponseData addUser(UsersVo userVo);

   public UserResponseData updateUser(Long usersId, String oldPassword, String newPassword);

    public  UserResponseData deleteUser(Long userId);



    public UserResponseData userLogin(String email, String password);



    public UserResponseData buyPremium(Long userId);
}
