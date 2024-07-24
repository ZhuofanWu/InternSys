package com.fancy.internsys.service;

import com.fancy.internsys.dto.StudentInfoReq;
import com.fancy.internsys.mapper.UserInfoMapper;
import com.fancy.internsys.pojo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public void changeStudentInfo(String uuid, StudentInfoReq studentInfoReq){
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setUuid(uuid);
        studentInfo.setName(studentInfoReq.getName());
        studentInfo.setGender(studentInfoReq.getGender());
        studentInfo.setStudent_id(studentInfoReq.getStudent_id());
        studentInfo.setStart_year(studentInfoReq.getStart_year());
        studentInfo.setMajor(studentInfoReq.getMajor());
        studentInfo.setTel(studentInfoReq.getTel());
        studentInfo.setEmail(studentInfoReq.getEmail());
        studentInfo.setSelf_intro(studentInfoReq.getSelf_intro());
        userInfoMapper.updateStudent(studentInfo);
    }

    public StudentInfo getStudentInfo(String uuid){
        return userInfoMapper.getStudentInfo(uuid);
    }


}
