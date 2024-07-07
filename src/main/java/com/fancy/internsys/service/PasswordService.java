package com.fancy.internsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PasswordService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String encodePassword(String rawPassword){
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    public boolean checkPassword(String inputPassword,String encodePassword){
        return bCryptPasswordEncoder.matches(inputPassword,encodePassword);
    }
}
