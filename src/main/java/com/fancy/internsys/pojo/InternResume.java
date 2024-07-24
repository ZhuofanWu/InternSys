package com.fancy.internsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternResume {
    private int id;
    private String filename;
    private String filepath;
    private String uuid;

    public InternResume(String filename, String filepath, String uuid) {
        this.filename = filename;
        this.filepath = filepath;
        this.uuid = uuid;
    }
}
