package com.fancy.internsys.mapper;

import com.fancy.internsys.pojo.CompanyInfo;
import com.fancy.internsys.pojo.StudentInfo;
import com.fancy.internsys.pojo.SysadminInfo;
import com.fancy.internsys.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    boolean existCompanyUUID(String uuid);

    boolean existStudentUUID(String uuid);

    boolean existTeacherUUID(String uuid);

    boolean existSysAdminUUID(String uuid);

    // Insert & Update
    void insertCompany(CompanyInfo companyInfo);

    void insertStudent(StudentInfo studentInfo);

    void insertTeacher(TeacherInfo teacherInfo);

    void insertSysAdmin(SysadminInfo sysadminInfo);

    void updateCompany(CompanyInfo companyInfo);

    void updateStudent(StudentInfo studentInfo);

    void updateTeacher(TeacherInfo teacherInfo);

    void updateSysAdmin(SysadminInfo sysadminInfo);

    // Select
    StudentInfo getStudentInfo(String uuid);

    CompanyInfo getCompanyInfo(String uuid);

    SysadminInfo getSysAdminInfo(String uuid);

    TeacherInfo getTeacherInfo(String uuid);
}
