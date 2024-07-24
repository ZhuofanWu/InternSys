package com.fancy.internsys.service;

import com.fancy.internsys.mapper.InternResumeMapper;
import com.fancy.internsys.pojo.InternResume;
import com.fancy.internsys.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private InternResumeMapper internResumeMapper;

    public void uploadFile(MultipartFile file, String uuid) throws IOException {
        String filename = file.getOriginalFilename();
        String filepath = "D:/upload/" + uuid + "_" + filename;
        File dest = new File(filepath);
        file.transferTo(dest);
        internResumeMapper.insertResume(new InternResume(filename, filepath, uuid));
    }

    public File downloadFile(int id){
        InternResume resume = internResumeMapper.selectById(id);
        if (resume == null) {
            throw new RuntimeException("File not found");
        }
        return new File(resume.getFilepath());
    }

    public File downloadFile(String filename) {
        InternResume resume = internResumeMapper.selectByFilename(filename);
        if (resume == null) {
            throw new RuntimeException("File not found");
        }
        return new File(resume.getFilepath());
    }

    public List<InternResume> getResumesByUuid(String uuid) {
        return internResumeMapper.selectByUuid(uuid);
    }

    public void deleteFile(int id) {
        InternResume resume = internResumeMapper.selectById(id);
        if (resume != null) {
            File file = new File(resume.getFilepath());
            if (file.exists()) {
                file.delete();
            }
            internResumeMapper.deleteById(id);
        }
    }
}
