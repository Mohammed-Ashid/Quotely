package com.example.quotely.demo.Controller;

import com.example.quotely.demo.Service.UserService;
import com.example.quotely.demo.Vo.ResponseData;
import com.example.quotely.demo.Vo.UsersVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
@Builder


public class UserController {
    private final UserService userService;
    @PostMapping("/addnew")
    public ResponseEntity<ResponseData> addUser(@RequestBody UsersVo userVo){
        ResponseData result=userService.addUser(userVo);
        return ResponseEntity.ok(result);
    }
}
