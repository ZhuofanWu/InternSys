package com.fancy.internsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternSubmit {
    private int id;
    private String name;
    private int job_id;
    private String job_name;
    private int resume_id;
    private int accept;

    public InternSubmit(String name, int job_id, String job_name, int resume_id, int accept) {
        this.name = name;
        this.job_id = job_id;
        this.job_name = job_name;
        this.resume_id = resume_id;
        this.accept = accept;
    }
}
