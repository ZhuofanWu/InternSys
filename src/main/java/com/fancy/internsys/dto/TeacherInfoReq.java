package com.fancy.internsys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherInfoReq {
    private String name;
    private String tel;
    private String email;

    public TeacherInfoReq(String name, String tel, String email) {
        this.name = name;
        this.tel = tel;
        this.email = email;
    }

    public TeacherInfoReq() {
    }
}
