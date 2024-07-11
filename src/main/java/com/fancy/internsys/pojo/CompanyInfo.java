package com.fancy.internsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfo {
    private int id;
    private String uuid;
    private String name;
    private String company_name;
    private String tel;
    private String email;
}
