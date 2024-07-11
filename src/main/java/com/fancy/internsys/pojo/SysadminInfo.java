package com.fancy.internsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysadminInfo {
    private int id;
    private String uuid;
    private String name;
    private String department;
    private String tel;
    private String email;
}
