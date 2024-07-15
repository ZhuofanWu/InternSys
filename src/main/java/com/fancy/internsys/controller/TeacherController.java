package com.fancy.internsys.controller;

import com.fancy.internsys.dto.TeacherInfoReq;
import com.fancy.internsys.pojo.TeacherInfo;
import com.fancy.internsys.service.TeacherService;
import com.fancy.internsys.util.JwtUtil;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/info")
    public ResponseEntity<?> changeTeacherInfo(@RequestHeader("Authorization") String token, @RequestBody TeacherInfoReq teacherInfoReq) {
        if(!jwtUtil.validateToken(token,"teacher")){
            return ResponseEntity.status(401).body("Not authorized");
        }
        teacherService.changeTeacherInfo(jwtUtil.extractUuid(token), teacherInfoReq);
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/info")
    public ResponseEntity<TeacherInfo> getTeacherInfo(@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token,"teacher")){
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(teacherService.getTeacherInfo(jwtUtil.extractUuid(token)));
    }
}
