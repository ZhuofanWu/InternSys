package com.fancy.internsys.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserReq {
    private String login_mail;
    private String password;
    private String role;

    public UserReq() {
    }

    public UserReq(String login_mail, String password, String role) {
        this.login_mail = login_mail;
        this.password = password;
        this.role = role;
    }
}
