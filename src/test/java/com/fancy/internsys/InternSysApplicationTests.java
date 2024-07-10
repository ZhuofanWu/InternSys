package com.fancy.internsys;

import com.fancy.internsys.controller.UserController;
import com.fancy.internsys.dto.UserReq;
import com.fancy.internsys.service.EmailService;
import com.fancy.internsys.service.UserService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InternSysApplicationTests {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserController userController;
    @Test
    void contextLoads(){

    }

}
