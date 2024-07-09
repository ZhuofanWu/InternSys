package com.fancy.internsys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordReq {
    private String login_mail;
    private String token;
    private String password;

    public ResetPasswordReq(String login_mail, String token, String password) {
        this.login_mail = login_mail;
        this.token = token;
        this.password = password;
    }

    public ResetPasswordReq() {
    }
}
