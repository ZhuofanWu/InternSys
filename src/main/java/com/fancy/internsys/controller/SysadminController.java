package com.fancy.internsys.controller;

import com.fancy.internsys.dto.SysAdminInfoReq;
import com.fancy.internsys.pojo.SysadminInfo;
import com.fancy.internsys.service.SysadminService;
import com.fancy.internsys.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class SysadminController {
    @Autowired
    private SysadminService sysadminService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/info")
    public ResponseEntity<?> changeSysAdminInfo(@RequestHeader("Authorization") String token, @RequestBody SysAdminInfoReq sysAdminInfoReq) {
        if(!jwtUtil.validateToken(token,"sysadmin")){
            return ResponseEntity.status(401).body("Not authorized");
        }
        sysadminService.changeSysadminInfo(jwtUtil.extractUuid(token), sysAdminInfoReq);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("/info")
    public ResponseEntity<SysadminInfo> getSysAdminInfo(@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token,"sysadmin")){
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok().body(sysadminService.getSysadminInfo(jwtUtil.extractUuid(token)));
    }
}
