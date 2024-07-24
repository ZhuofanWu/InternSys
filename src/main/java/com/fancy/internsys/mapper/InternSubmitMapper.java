package com.fancy.internsys.mapper;

import com.fancy.internsys.pojo.InternSubmit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface InternSubmitMapper {
    @Insert("INSERT INTO intern_submit (name, job_id, job_name, resume_id, accept) VALUES (#{name}, #{job_id},#{job_name}, #{resume_id}, #{accept})")
    void insertSubmit(InternSubmit submit);

    @Select("SELECT left_number FROM intern_jobs WHERE id=#{id}")
    int searchJobLeftNumber(String id);

    @Update("UPDATE intern_jobs SET resume_number = resume_number + 1 where id=#{id}")
    void updateResumeNumber(int id);

    @Update("UPDATE intern_jobs SET left_number =  left_number - 1  WHERE id=#{id} AND left_number > 0")
    void updateLeftNumber(int id);
}
