package com.fancy.internsys.mapper;

import com.fancy.internsys.pojo.InternSubmit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InternSubmitMapper {
    int searchJobLeftNumber(int jobId);

    void insertSubmit(InternSubmit submit);

    void submitJob(int jobId);

    void cancelSubmit(int resumeSubmitId);

    void cancelSubmitJob(int jobId);
}
