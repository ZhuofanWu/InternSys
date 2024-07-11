package com.fancy.internsys.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {
    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user_company_info WHERE uuid = #{uuid}")
    public boolean existCompanyUUID(String uuid);

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user_student_info WHERE uuid = #{uuid}")
    public boolean existStudentUUID(String uuid);

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user_teacher_info WHERE uuid = #{uuid}")
    public boolean existTeacherUUID(String uuid);

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user_sysadmin_info WHERE uuid = #{uuid}")
    public boolean existSysAdminUUID(String uuid);

    //Insert & Update
}
