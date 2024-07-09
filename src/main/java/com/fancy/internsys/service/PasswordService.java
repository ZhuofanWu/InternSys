package com.fancy.internsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

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

    public String generateRandomToken(int length) {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String combinedChars = upperCaseLetters + numbers;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(combinedChars.length());
            sb.append(combinedChars.charAt(index));
        }
        return sb.toString();
    }
}
