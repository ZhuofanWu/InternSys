package com.fancy.internsys.controller;

import com.fancy.internsys.dto.UserReq;
import com.fancy.internsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserReq userReq){
        boolean isValid= userService.validateUser(userReq.getLogin_mail(),userReq.getPassword(),userReq.getRole());
        if (isValid) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid login credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserReq userReq){
        boolean regState = userService.registerUser(userReq.getLogin_mail(), userReq.getPassword(), userReq.getRole());
        if(regState){
            System.out.println(0);
            return ResponseEntity.ok("Register successful");
        }else {
            System.out.println(1);
            return ResponseEntity.status(401).body("Account exist");
        }
    }
}
