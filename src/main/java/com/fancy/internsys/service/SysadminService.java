package com.fancy.internsys.service;

import com.fancy.internsys.mapper.UserInfoMapper;
import com.fancy.internsys.dto.SysInfoReq;
import com.fancy.internsys.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysadminService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private JwtUtil jwtUtil;

    public void changeSysadminInfo(String uuid, SysInfoReq sysadminInfoReq) {

    }
}
