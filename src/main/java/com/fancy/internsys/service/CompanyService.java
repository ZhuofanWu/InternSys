package com.fancy.internsys.service;

import com.fancy.internsys.dto.CompanyInfoReq;
import com.fancy.internsys.mapper.InternJobsMapper;
import com.fancy.internsys.mapper.UserInfoMapper;
import com.fancy.internsys.pojo.CompanyInfo;
import com.fancy.internsys.pojo.InternJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private InternJobsMapper internJobsMapper;

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

    public InternJob getJob(int id){
        return internJobsMapper.getJobById(id);
    }

    public void deleteJob(int id) {
        internJobsMapper.deleteJobById(id);
    }

    public void updateJob(InternJob internJob) {
        internJobsMapper.updateJob(internJob);
    }

    public int getJobNumber(){
        return internJobsMapper.getJobNumber();
    }

    public void insertJob(InternJob job){
        internJobsMapper.insertJob(job);
    }

    public List<InternJob> getJobByPage(int limit, int offset){
        return internJobsMapper.getJobsByPage(limit,offset);
    }

    public void deleteJobsByList(List<Integer> ids){
        internJobsMapper.deleteJobsByList(ids);
    }
}
