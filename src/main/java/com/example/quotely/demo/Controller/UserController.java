package com.example.quotely.demo.Controller;

import com.example.quotely.demo.DataTransferObject.BuyPremiumRequest;
import com.example.quotely.demo.DataTransferObject.UpdateUserRequest;
import com.example.quotely.demo.DataTransferObject.UserLoginRequest;
import com.example.quotely.demo.Responses.UserResponseData;
import com.example.quotely.demo.Service.UserService;
import com.example.quotely.demo.Responses.QuotesResponseData;
import com.example.quotely.demo.Vo.UsersVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
@Builder


public class UserController {
    private final UserService userService;
    @PostMapping("/addnew")
    public ResponseEntity<UserResponseData> addUser(@RequestBody UsersVo userVo){
        UserResponseData result=userService.addUser(userVo);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateuser")
    public ResponseEntity<UserResponseData> updateUser(@RequestBody UpdateUserRequest request){
        UserResponseData result=userService.updateUser(request.getUsersId(),request.getOldPassword(),request.getNewPassword(),request.getAuthkey());
        return  ResponseEntity.ok(result);
    }

    @PostMapping("/deleteuser")
    public ResponseEntity<UserResponseData> deleteUser(@RequestBody Long userId){
        UserResponseData result=userService.deleteUser(userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/userlogin")
    public ResponseEntity<UserResponseData> userLogin(@RequestBody UserLoginRequest request){
        UserResponseData result=userService.userLogin(request.getEmail(),request.getPassword());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/buypremium")
    public ResponseEntity<UserResponseData> buyPremium(@RequestBody BuyPremiumRequest buyPremiumRequest){
        UserResponseData result=userService.buyPremium(buyPremiumRequest.getUserId(), buyPremiumRequest.getDuration(), buyPremiumRequest.getAuthKey());
        return ResponseEntity.ok(result);
    }


}
