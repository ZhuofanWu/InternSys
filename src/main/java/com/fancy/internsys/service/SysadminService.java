package com.fancy.internsys.service;

import com.fancy.internsys.mapper.UserInfoMapper;
import com.fancy.internsys.dto.SysAdminInfoReq;
import com.fancy.internsys.pojo.SysadminInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysadminService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public void changeSysadminInfo(String uuid, SysAdminInfoReq sysadminInfoReq) {
        SysadminInfo sysadminInfo = new SysadminInfo();
        sysadminInfo.setUuid(uuid);
        sysadminInfo.setName(sysadminInfoReq.getName());
        sysadminInfo.setDepartment(sysadminInfoReq.getDepartment());
        sysadminInfo.setTel(sysadminInfoReq.getTel());
        sysadminInfo.setEmail(sysadminInfoReq.getEmail());
        userInfoMapper.updateSysAdmin(sysadminInfo);
    }

    public SysadminInfo getSysadminInfo(String uuid) {
        return userInfoMapper.getSysAdminInfo(uuid);
    }
}
