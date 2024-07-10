package com.fancy.internsys.pojo;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordReset {
    private String uuid;
    private String login_mail;
    private String token;
    private Timestamp expire_time;
}
