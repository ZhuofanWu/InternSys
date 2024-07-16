package com.fancy.internsys.mapper;

import com.fancy.internsys.pojo.CompanyInfo;
import com.fancy.internsys.pojo.StudentInfo;
import com.fancy.internsys.pojo.SysadminInfo;
import com.fancy.internsys.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserInfoMapper {
    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user_company_info WHERE uuid = #{uuid}")
    boolean existCompanyUUID(String uuid);

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user_student_info WHERE uuid = #{uuid}")
    boolean existStudentUUID(String uuid);

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user_teacher_info WHERE uuid = #{uuid}")
    boolean existTeacherUUID(String uuid);

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM user_sysadmin_info WHERE uuid = #{uuid}")
    boolean existSysAdminUUID(String uuid);

    //Insert & Update
    @Insert("INSERT into user_company_info (uuid, name, company_name, tel, email) values (#{uuid},#{name},#{company_name},#{tel},#{email})")
    void insertCompany(CompanyInfo companyInfo);

    @Insert("INSERT into user_student_info (uuid, name, gender, start_year, major, tel, email, self_intro) values (#{uuid}, #{name}, #{gender}, #{start_year}, #{major}, #{tel}, #{email}, #{self_intro})")
    void insertStudent(StudentInfo studentInfo);

    @Insert("INSERT into user_teacher_info (uuid, name, tel, email) VALUES (#{uuid}, #{name}, #{tel}, #{email})")
    void insertTeacher(TeacherInfo teacherInfo);

    @Insert("INSERT INTO user_sysadmin_info (uuid, name, department, tel, email) VALUES (#{uuid}, #{name},#{department}, #{tel}, #{email})")
    void insertSysAdmin(SysadminInfo sysadminInfo);

    @Update("UPDATE user_company_info SET name = #{name}, company_name = #{companyName}, tel = #{tel}, email = #{email} WHERE uuid = #{uuid}")
    void updateCompany(CompanyInfo companyInfo);

    @Update("UPDATE user_student_info SET name = #{name}, gender = #{gender}, start_year = #{startYear}, major = #{major}, tel = #{tel}, email = #{email}, self_intro = #{selfIntro} WHERE uuid = #{uuid}")
    void updateStudent(StudentInfo studentInfo);

    @Update("UPDATE user_teacher_info SET name = #{name}, tel = #{tel}, email = #{email} WHERE uuid = #{uuid}")
    void updateTeacher(TeacherInfo teacherInfo);

    @Update("UPDATE user_sysadmin_info SET name = #{name}, department = #{department}, tel = #{tel}, email = #{email} WHERE uuid = #{uuid}")
    void updateSysAdmin(SysadminInfo sysadminInfo);

    @Select("SELECT * FROM user_student_info where uuid=#{uuid}")
    StudentInfo getStudentInfo(String uuid);

    @Select("SELECT * FROM user_company_info where uuid=#{uuid}")
    CompanyInfo getCompanyInfo(String uuid);

    @Select("SELECT * FROM user_sysadmin_info WHERE uuid=#{uuid}")
    SysadminInfo getSysAdminInfo(String uuid);

    @Select("SELECT * FROM user_teacher_info WHERE uuid=#{uuid}")
    TeacherInfo getTeacherInfo(String uuid);
}
