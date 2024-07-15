package com.fancy.internsys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysAdminInfoReq {
    private String name;
    private String department;
    private String tel;
    private String email;

    public SysAdminInfoReq(String name, String department, String tel, String email) {
        this.name = name;
        this.department = department;
        this.tel = tel;
        this.email = email;
    }

    public SysAdminInfoReq() {
    }
}
