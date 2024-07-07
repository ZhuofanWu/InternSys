package com.fancy.internsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIdentity {
    private String UUID;
    private String login_mail;
    private String password;
    private String role;
}
