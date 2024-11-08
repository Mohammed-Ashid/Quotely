package com.example.quotely.demo.ServiceImpl;

import com.example.quotely.demo.Entity.Users;
import com.example.quotely.demo.Enums.GeneralStatus;
import com.example.quotely.demo.Enums.PremiumStatus;
import com.example.quotely.demo.Mapper.UserMapper;
import com.example.quotely.demo.Repository.UserRepository;
import com.example.quotely.demo.Responses.UserData;
import com.example.quotely.demo.Responses.UserResponseData;
import com.example.quotely.demo.Service.UserService;
import com.example.quotely.demo.Vo.UsersVo;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.example.quotely.demo.Vo.AuthKeyGenerator.generateAuthKey;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    private final PasswordEncoder passwordEncoder;
    @Override

    public UserResponseData addUser(UsersVo userVo) {
//        String encodedPassword = passwordEncoder.encode(userVo.getPassword());
        String authKey = generateAuthKey();
        Users users= UserMapper.toUser(userVo);
        String uniqueID = UUID.randomUUID().toString();
//        user.setPassword(encodedPassword);
        users.setAuthKey(authKey);
        Date currentDate = new Date();
        users.setCreatedAt(currentDate);
        users.setUpdatedAt(currentDate);
        users.setThemesId(1L);
        users.setPremiumEndDate(null);
        users.setPremiumStartDate(null);
        userRepository.save(users);
        UserData dataResult = UserData.builder()
                .id(users.getUsersId())
                .type("user")
                .name(users.getFirstName())
                .createdAt(users.getCreatedAt())
                .lastUpdated(users.getUpdatedAt())
                .themeId(users.getThemesId())
                .status(users.getStatus())
                .authKey(users.getAuthKey())
                .premiumStatus(users.getPremiumStatus())
                .response("DETAILS OF NEW USER,THEMES ID IS DEFAULT")
                .build();
        UserResponseData responseData= UserResponseData.builder()
                .code("success")
                .status("success")
                .message("New User Registered")
                .data(Optional.ofNullable(Collections.singletonList(dataResult)))
                .build();
        return responseData;
    }

    @Override

    public UserResponseData updateUser(Long usersId, String oldPassword, String newPassword, String authkey) {
        Optional<Users> users = userRepository.findById(usersId);
        Users updateUser = users.get();

        UserResponseData responseData = new UserResponseData();
        if (users.isPresent()&&(Objects.equals(updateUser.getPassword(), oldPassword))&&authkey==updateUser.getAuthKey()) {


            updateUser.setPassword(newPassword);
            Date currentDate = new Date();
            updateUser.setUpdatedAt(currentDate);

            userRepository.save(updateUser);

            UserData dataResult = UserData.builder()
                    .id(updateUser.getUsersId())
                    .type("user")
                    .name(updateUser.getFirstName())
                    .createdAt(updateUser.getCreatedAt())
                    .lastUpdated(updateUser.getUpdatedAt())
                    .themeId(updateUser.getThemesId())
                    .status(updateUser.getStatus())
                    .premiumStatus(updateUser.getPremiumStatus())
                    .response("DETAILS AFTER UPDATION")
                    .build();
            responseData = UserResponseData.builder()
                    .code("success")
                    .status("success")
                    .message("Password Updated Successfully")
                    .data(Optional.ofNullable(Collections.singletonList(dataResult)))
                    .build();



        }else {
            UserData dataResult = UserData.builder()

                    .response("NO UPDATIONS MADE")
                    .build();
            responseData = UserResponseData.builder()
                    .code("success")
                    .status("success")
                    .message("Old Password Dosen't Match or user dosent match")
                    .data(Optional.ofNullable(Collections.singletonList(dataResult)))
                    .build();

        }
        return responseData;
    }

    @Override
    public UserResponseData deleteUser(Long userId) {
        return null;
    }

    @Override
    public UserResponseData userLogin(String email, String password) {

            UserResponseData userResponseData=new UserResponseData();
        // Fetch user by email
        Optional<Users> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            if (user.getPassword().equals(password)) {
                if(user.getStatus().equals(GeneralStatus.ACTIVE)) {
                    UserData userData = UserData.builder()
                            .id(user.getUsersId())
                            .type("user")
                            .status(user.getStatus())
                            .themeId(user.getThemesId())
                            .name(user.getFirstName())
                            .premiumStatus(user.getPremiumStatus())
                            .lastUpdated(user.getUpdatedAt())
                            .premiumEndDate(user.getPremiumEndDate())
                            .createdAt(user.getCreatedAt())
                            .authKey(user.getAuthKey())
                            .response("User login posiible")
                            .build();
                    userResponseData = UserResponseData.builder()
                            .code("Success")
                            .status("Ok")
                            .message("User Login")
                            .data(Optional.ofNullable(Collections.singletonList(userData)))
                            .build();
                }
                else{
                    UserData userData=UserData.builder()
                            .response("User is set INACTIVE")
                            .build();
                    userResponseData=UserResponseData.builder()
                            .code("Success")
                            .status("Ok")
                            .message("INACTIVE")
                            .data(Optional.ofNullable(Collections.singletonList(userData)))
                            .build();

                }

            }
            else {
                // Password does not match, return error response
                UserData userData=UserData.builder()
                        .response("passwords dosent match")
                        .build();
                userResponseData=UserResponseData.builder()
                        .code("Success")
                        .status("Ok")
                        .message("Incorrect password")
                        .data(Optional.ofNullable(Collections.singletonList(userData)))
                        .build();
            }

        }
        else {
            // Password does not match, return error response
            UserData userData=UserData.builder()
                    .response("User Dosent Exist")
                    .build();
            userResponseData=UserResponseData.builder()
                    .code("Success")
                    .status("Ok")
                    .message("User Not Found")
                    .data(Optional.ofNullable(Collections.singletonList(userData)))
                    .build();
        }
        return userResponseData;


    }

    @Override
    public UserResponseData buyPremium(Long usersId, Long duration, String authKey) {
        Optional<Users> userOptional = userRepository.findById(usersId);
        String auth=authKey;
        Users user=userOptional.get();
        String authky= user.getAuthKey();

        if (userOptional.isPresent()) {

            LocalDate currentDate = LocalDate.now();
            user.setPremiumStartDate(currentDate);
            user.setPremiumStatus(PremiumStatus.ACTIVE);
            LocalDate premiumEndDate = currentDate.plusMonths(duration);
            user.setPremiumEndDate(premiumEndDate);

            userRepository.save(user);
        }
        return null;
    }


}
