package com.fancy.internsys.mapper;

import com.fancy.internsys.pojo.InternJob;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface InternJobsMapper {
    @Select("SELECT * from intern_jobs where id=#{id}")
    InternJob getJobById(int id);

    @Delete("delete from intern_jobs where id=#{id}")
    void deleteJobById(int id);

    @Update("UPDATE intern_jobs SET " +
            "job_name = #{job_name}, " +
            "company_name = #{company_name}, " +
            "expire_time = #{expire_time}, " +
            "open_receive = #{open_receive}, " +
            "grade = #{grade}, " +
            "job_require = #{job_require}, " +
            "competitive = #{competitive}, " +
            "hc_number = #{hc_number}, " +
            "resume_number = #{resume_number}, " +
            "left_number = #{left_number} " +
            "WHERE id = #{id}")
    void updateJob(InternJob job);


}
