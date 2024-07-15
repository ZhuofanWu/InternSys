package com.fancy.internsys.service;

import com.fancy.internsys.dto.TeacherInfoReq;
import com.fancy.internsys.mapper.UserInfoMapper;
import com.fancy.internsys.pojo.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public void changeTeacherInfo(String uuid, TeacherInfoReq teacherInfoReq) {
        TeacherInfo teacherInfo = new TeacherInfo();
        teacherInfo.setUuid(uuid);
        teacherInfo.setName(teacherInfoReq.getName());
        teacherInfo.setTel(teacherInfoReq.getTel());
        teacherInfo.setEmail(teacherInfoReq.getEmail());
        userInfoMapper.updateTeacher(teacherInfo);
    }

    public TeacherInfo getTeacherInfo(String uuid) {
        return userInfoMapper.getTeacherInfo(uuid);
    }
}
