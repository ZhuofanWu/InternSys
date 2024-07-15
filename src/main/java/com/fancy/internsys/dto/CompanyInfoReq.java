package com.fancy.internsys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyInfoReq {
    private String name;
    private String company_name;
    private String tel;
    private String email;

    public CompanyInfoReq(String name, String company_name, String tel, String email) {
        this.name = name;
        this.company_name = company_name;
        this.tel = tel;
        this.email = email;
    }

    public CompanyInfoReq() {
    }
}
