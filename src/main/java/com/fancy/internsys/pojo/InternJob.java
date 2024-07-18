package com.fancy.internsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternJob {
    private int id;
    private String job_name;
    private String company_name;
    private Timestamp expire_time;
    private boolean open_receive;
    private int grade;
    private String job_require;
    private boolean competitive;
    private int hc_number;
    private int resume_number;
    private int left_number;
}
