package com.fancy.internsys.dto;

public class UserReq {
    private String login_mail;
    private String password;
    private String role;

    public String getLogin_mail() {
        return login_mail;
    }

    public void setLogin_mail(String login_mail) {
        this.login_mail = login_mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserReq() {
    }

    public UserReq(String login_mail, String password, String role) {
        this.login_mail = login_mail;
        this.password = password;
        this.role = role;
    }
}
