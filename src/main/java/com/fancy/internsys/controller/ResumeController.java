package com.fancy.internsys.controller;

import com.fancy.internsys.pojo.InternResume;
import com.fancy.internsys.service.FileService;
import com.fancy.internsys.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private FileService fileService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/upload")
    public String uploadFile(@RequestHeader("Authorization") String token, @RequestParam("file") MultipartFile file) {
        if(!jwtUtil.validateToken(token,"student")){
            return "false";
        }
        String uuid = jwtUtil.extractUuid(token);
        try {
            fileService.uploadFile(file, uuid);
            return "File uploaded successfully";
        } catch (IOException e) {
            return "File upload failed: " + e.getMessage();
        }
    }

    @GetMapping("/download/{filename}")
    public void downloadFile(@PathVariable String filename, HttpServletResponse response) {
        try {
            File file = fileService.downloadFile(filename);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            FileInputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public List<InternResume> listFiles(@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token,"student")){
            return null;
        }
        String uuid = jwtUtil.extractUuid(token);
        return fileService.getResumesByUuid(uuid);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFile(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        if(!jwtUtil.validateToken(token,"student")){
            return "false";
        }
        fileService.deleteFile(id);
        return "File deleted successfully";
    }
}
