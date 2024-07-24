package com.fancy.internsys.service;

import com.fancy.internsys.mapper.InternResumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmitService {
    @Autowired
    private InternResumeMapper internResumeMapper;
}
