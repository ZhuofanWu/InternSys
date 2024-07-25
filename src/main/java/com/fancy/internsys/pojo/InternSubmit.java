package com.fancy.internsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternSubmit {
    private int id;
    private String uuid; //FK
    private String name;
    private int job_id; //FK
    private String job_name;
    private int resume_id; //FK
    private int accept; //0 unhandled, -1 rejected, 1 accepted

    public InternSubmit(String name, String uuid, int job_id, int resume_id, String job_name, int accept) {
        this.name = name;
        this.uuid = uuid;
        this.job_id = job_id;
        this.resume_id = resume_id;
        this.job_name = job_name;
        this.accept = accept;
    }
}
