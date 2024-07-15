package com.fancy.internsys.controller;

import com.fancy.internsys.dto.StudentInfoReq;
import com.fancy.internsys.pojo.StudentInfo;
import com.fancy.internsys.service.StudentService;
import com.fancy.internsys.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/info")
    public ResponseEntity<?> changeInfo(@RequestHeader("Authorization") String token,@RequestBody StudentInfoReq studentInfoReq) {
        if(!jwtUtil.validateToken(token,"student")){
            return ResponseEntity.status(401).body("Not authorized");
        }
        studentService.changeStudentInfo(jwtUtil.extractUuid(token), studentInfoReq);
        return ResponseEntity.ok().body("Done!");
    }

    @GetMapping("/info")
    public ResponseEntity<StudentInfo> getStudentInfo(@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token,"student")){
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok().body(studentService.getStudentInfo(jwtUtil.extractUuid(token)));
    }
}
