package com.fancy.internsys.controller;

import com.fancy.internsys.dto.ResetPasswordReq;
import com.fancy.internsys.dto.UserReq;
import com.fancy.internsys.service.UserService;
import com.fancy.internsys.util.JwtUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserReq userReq){
        boolean isValid= userService.validateUser(userReq.getLogin_mail(),userReq.getPassword(),userReq.getRole());
        if (isValid) {
            String uuid = userService.getUserUUID(userReq.getLogin_mail());
            String token = jwtUtil.generateToken(uuid,userReq.getLogin_mail(),userReq.getPassword());
            return ResponseEntity.ok("Login successful, token: " + token);
        } else {
            return ResponseEntity.status(401).body("Invalid login credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserReq userReq){
        boolean regState = userService.registerUser(userReq.getLogin_mail(), userReq.getPassword(), userReq.getRole());
        if(regState){
            return ResponseEntity.ok("Register successful");
        }else {
            return ResponseEntity.status(401).body("Account exist");
        }
    }

    @GetMapping("/resetpassword")
    public ResponseEntity<?> preResetPassword(@RequestParam("email") String email) throws MessagingException {
        boolean status = userService.searchUser(email);
        if(!status){
            return ResponseEntity.status(401).body("User not exist");
        }else {
            userService.preResetPasswordUser(email);
            return ResponseEntity.ok("Email sent");
        }
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<?> ResetPassword(@RequestBody ResetPasswordReq req){
        boolean status = userService.searchUser(req.getLogin_mail());
        if(!status){
            return ResponseEntity.status(401).body("User not exist");
        }else {
            if(!userService.resetPassword(req.getLogin_mail(), req.getToken(), req.getPassword())){
                return ResponseEntity.status(401).body("Token expired/Wrong token");
            }else {
                return ResponseEntity.ok("Password reset");
            }
        }
    }
}
