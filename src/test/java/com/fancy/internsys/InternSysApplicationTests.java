package com.fancy.internsys;

import com.fancy.internsys.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InternSysApplicationTests {
    @Autowired
    private EmailService emailService;
    @Test
    void contextLoads() {
        //emailService.sendMail();
    }

}
