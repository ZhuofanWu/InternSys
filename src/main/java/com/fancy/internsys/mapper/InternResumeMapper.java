package com.fancy.internsys.mapper;

import com.fancy.internsys.pojo.InternResume;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InternResumeMapper {
    @Insert("INSERT INTO intern_resume (filename, filepath, uuid) VALUES (#{filename}, #{filepath}, #{uuid})")
    void insertResume(InternResume resume);

    @Select("SELECT * FROM intern_resume WHERE uuid=#{uuid}")
    List<InternResume> selectByUuid(String uuid);

    @Select("SELECT * FROM intern_resume WHERE filename=#{filename}")
    InternResume selectByFilename(String filename);

    @Select("SELECT * FROM intern_resume WHERE id=#{id}")
    InternResume selectById(int id);

    //特别注意 删除简历会删除该简历所有的投递
    @Delete("DELETE FROM intern_resume WHERE id=#{id}")
    void deleteById(int id);
}
