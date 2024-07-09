package com.fancy.internsys.service;

import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;
import org.apache.catalina.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDate;
import java.util.Date;

@Service
public class EmailService {
    @Autowired(required = false)
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendMail(String to, String subject, String content,boolean isHtml) throws MessagingException {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, isHtml);
            messageHelper.setSentDate(new Date());
            javaMailSender.send(messageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new MessagingException("邮件发送失败", e);
        }
    }

    public String resetPasswordEmailPre(String name,String token){
        Context context = new Context();
        LocalDate today = LocalDate.now();
        context.setVariable("name", name);
        context.setVariable("token",token);
        context.setVariable("date",today.toString());
        String content = templateEngine.process("PasswordResetTemplate.html",context);
        return content;
    }
}
