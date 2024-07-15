package com.fancy.internsys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInfoReq {
    private String name;
    private String gender;
    private String student_id;
    private int start_year;
    private String major;
    private String tel;
    private String email;
    private String self_intro;

    public StudentInfoReq() {
    }

    public StudentInfoReq(String name, String student_id, String gender, int start_year, String major, String tel, String email, String self_intro) {
        this.name = name;
        this.student_id = student_id;
        this.gender = gender;
        this.start_year = start_year;
        this.major = major;
        this.tel = tel;
        this.email = email;
        this.self_intro = self_intro;
    }
}
