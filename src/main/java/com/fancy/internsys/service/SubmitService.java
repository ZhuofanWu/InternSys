package com.fancy.internsys.service;

import com.fancy.internsys.mapper.InternResumeMapper;
import com.fancy.internsys.mapper.InternSubmitMapper;
import com.fancy.internsys.pojo.InternSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubmitService {
    @Autowired
    private InternResumeMapper resumeMapper;
    @Autowired
    private InternSubmitMapper submitMapper;

    @Transactional
    public void submitResume(String uuid, String name, int job_id, String job_name, int resume_id) {
        InternSubmit info = new InternSubmit();
        info.setUuid(uuid);
        info.setName(name);
        info.setJob_id(job_id);
        info.setJob_name(job_name);
        info.setResume_id(resume_id);
        info.setAccept(0);
        submitMapper.insertSubmit(info);
        submitMapper.submitJob(job_id);
    }

    @Transactional
    public void cancelSubmit(int submitId, int jobId) {
        submitMapper.cancelSubmit(submitId);
        submitMapper.cancelSubmitJob(jobId);
    }
}
