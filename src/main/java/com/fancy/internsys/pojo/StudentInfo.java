package com.fancy.internsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfo {
    private int id;
    private String uuid;
    private String name;
    private String gender;
    private int start_year;
    private String major;
    private String tel;
    private String email;
    private String self_intro;
}
