package com.fancy.internsys.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternJobReq {
    private String job_name;
    private String company_name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    private Timestamp expire_time;
    private boolean open_receive;
    private int grade;
    private String job_require;
    private boolean competitive;
    private int hc_number;
    private int resume_number;
    private int left_number;
}
