package com.fancy.internsys.service;

import com.fancy.internsys.dto.CompanyInfoReq;
import com.fancy.internsys.mapper.UserInfoMapper;
import com.fancy.internsys.pojo.CompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public void changeCompanyInfo(String uuid, CompanyInfoReq companyInfoReq) {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setUuid(uuid);
        companyInfo.setName(companyInfoReq.getName());
        companyInfo.setCompany_name(companyInfoReq.getCompany_name());
        companyInfo.setTel(companyInfoReq.getTel());
        companyInfo.setEmail(companyInfoReq.getEmail());
        userInfoMapper.updateCompany(companyInfo);
    }

    public CompanyInfo getCompanyInfo(String uuid) {
        return userInfoMapper.getCompanyInfo(uuid);
    }
}
