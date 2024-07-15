package com.fancy.internsys.controller;

import com.fancy.internsys.dto.CompanyInfoReq;
import com.fancy.internsys.pojo.CompanyInfo;
import com.fancy.internsys.service.CompanyService;
import com.fancy.internsys.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/info")
    public ResponseEntity<?> changeInfo(@RequestHeader("Authorization") String token, @RequestBody CompanyInfoReq companyInfoReq) {
        if(!jwtUtil.validateToken(token,"company")){
            return ResponseEntity.status(401).body("Not authorized");
        }
        companyService.changeCompanyInfo(jwtUtil.extractUuid(token), companyInfoReq);
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/info")
    public ResponseEntity<CompanyInfo> getCompanyInfo(@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token,"company")){
            return ResponseEntity.status(401).body(null);
        }
        CompanyInfo companyInfo = companyService.getCompanyInfo(jwtUtil.extractUuid(token));
        return ResponseEntity.ok().body(companyInfo);
    }
}
