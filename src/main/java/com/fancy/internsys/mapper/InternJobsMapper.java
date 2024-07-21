package com.fancy.internsys.mapper;

import com.fancy.internsys.pojo.InternJob;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InternJobsMapper {
    InternJob getJobById(int id);

    void deleteJobById(int id);

    void updateJob(InternJob job);

    int getJobNumber();

    List<InternJob> getJobsByPage(@Param("limit") int limit, @Param("offset") int offset);

    void insertJob(InternJob job);

    void deleteJobsByList(@Param("idList") List<Integer> idList);
}
