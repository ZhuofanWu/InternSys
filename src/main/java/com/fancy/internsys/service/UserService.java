package com.fancy.internsys.service;

import com.fancy.internsys.mapper.UserMapper;
import com.fancy.internsys.pojo.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordService passwordService;

    public boolean registerUser(String email,String rawPassword,String role){
        UserIdentity newUser;
        newUser = userMapper.searchUser(email);
        if(newUser!= null){ //如果存在这个用户，拒绝再次注册
            return false;
        }
        newUser=new UserIdentity();
        String uuid= UUID.randomUUID().toString();
        newUser.setUUID(uuid);
        newUser.setLogin_mail(email);
        newUser.setPassword(passwordService.encodePassword(rawPassword));
        newUser.setRole(role);
        userMapper.addUser(newUser);
        return true;
    }

    public boolean validateUser(String email,String rawPassword,String role){
        UserIdentity loginUser = userMapper.searchUser(email);
        if(loginUser==null){ //不存在用户
            return false;
        }
        if(passwordService.checkPassword(rawPassword,loginUser.getPassword())&& Objects.equals(role, loginUser.getRole())){ //验证密码和身份
            return true;
        }else {
            return false;
        }
    }
}
