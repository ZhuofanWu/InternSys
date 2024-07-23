package com.fancy.internsys.controller;

import com.fancy.internsys.dto.CompanyInfoReq;
import com.fancy.internsys.dto.InternJobReq;
import com.fancy.internsys.pojo.CompanyInfo;
import com.fancy.internsys.pojo.InternJob;
import com.fancy.internsys.service.CompanyService;
import com.fancy.internsys.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 处理公司相关请求的控制器。
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 修改公司信息。
     *
     * @param token  请求头中的授权令牌
     * @param companyInfoReq  请求体中的公司信息请求对象
     * @return 返回修改结果
     */
    @PostMapping("/info")
    public ResponseEntity<?> changeInfo(@RequestHeader("Authorization") String token, @RequestBody CompanyInfoReq companyInfoReq) {
        if (!jwtUtil.validateToken(token, "company")) {
            return ResponseEntity.status(401).body("Not authorized");
        }
        companyService.changeCompanyInfo(jwtUtil.extractUuid(token), companyInfoReq);
        return ResponseEntity.ok().body("success");
    }

    /**
     * 获取公司信息。
     *
     * @param token 请求头中的授权令牌
     * @return 返回公司信息对象
     */
    @GetMapping("/info")
    public ResponseEntity<CompanyInfo> getCompanyInfo(@RequestHeader("Authorization") String token) {
        if (!jwtUtil.validateToken(token, "company")) {
            return ResponseEntity.status(401).body(null);
        }
        CompanyInfo companyInfo = companyService.getCompanyInfo(jwtUtil.extractUuid(token));
        return ResponseEntity.ok().body(companyInfo);
    }

    /**
     * 获取职位总数。
     *
     * @param token 请求头中的授权令牌
     * @return 返回职位总数
     */
    @GetMapping("/api/total")
    public ResponseEntity<Integer> getTotalJobs(@RequestHeader("Authorization") String token) {
        if (!jwtUtil.validateToken(token, "company")) {
            return ResponseEntity.status(401).body(null);
        }
        Integer num = companyService.getJobNumber();
        return ResponseEntity.ok().body(num);
    }

    /**
     * 根据 ID 获取职位信息。
     *
     * @param token 请求头中的授权令牌
     * @param id    职位 ID
     * @return 返回职位信息对象
     */
    @GetMapping("/job/{id}")
    public ResponseEntity<InternJob> getJob(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id) {
        if (!jwtUtil.validateToken(token, "company")) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok().body(companyService.getJob(id));
    }

    /**
     * 分页获取职位信息。
     *
     * @param token  请求头中的授权令牌
     * @param limit  每页数量
     * @param offset 偏移量，从offset+1条开始返回
     * @return 返回职位信息列表
     */
    @GetMapping("/job")
    public ResponseEntity<List<InternJob>> getJobs(@RequestHeader("Authorization") String token, @RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset) {
        if (!jwtUtil.validateToken(token, "company")) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok().body(companyService.getJobByPage(limit, offset));
    }

    /**
     * 添加职位信息。
     *
     * @param token         请求头中的授权令牌
     * @param internJobReq  请求体中的职位信息请求对象
     * @return 返回添加结果
     */
    @PostMapping("/job")
    public ResponseEntity<?> addJob(@RequestHeader("Authorization") String token, @RequestBody InternJobReq internJobReq) {
        if (!jwtUtil.validateToken(token, "company")) {
            return ResponseEntity.status(401).body(null);
        }
        companyService.insertJob(internJobReq);
        return ResponseEntity.ok().body("success");
    }

    /**
     * 更新职位信息。
     *
     * @param token    请求头中的授权令牌
     * @param id       职位 ID
     * @param internJob 请求体中的职位信息对象
     * @return 返回更新结果
     */
    @PostMapping("/job/{id}")
    public ResponseEntity<?> updateJob(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id, @RequestBody InternJob internJob) {
        if (!jwtUtil.validateToken(token, "company")) {
            return ResponseEntity.status(401).body(null);
        }
        if (id != internJob.getId()) {
            return ResponseEntity.status(401).body("id not match");
        }
        companyService.updateJob(internJob);
        return ResponseEntity.ok().body("success");
    }

    /**
     * 删除职位信息。
     *
     * @param token 请求头中的授权令牌
     * @param id    职位 ID
     * @return 返回删除结果
     */
    @DeleteMapping("/job/{id}")
    public ResponseEntity<?> deleteJob(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id) {
        if (!jwtUtil.validateToken(token, "company")) {
            return ResponseEntity.status(401).body(null);
        }
        companyService.deleteJob(id);
        return ResponseEntity.ok().body("success");
    }

    /**
     * 批量删除职位信息。
     *
     * @param token       请求头中的授权令牌
     * @param requestBody 请求体中的职位 ID 列表
     * @return 返回删除结果
     */
    @DeleteMapping("/job/deletejobs")
    public ResponseEntity<?> deleteJobs(@RequestHeader("Authorization") String token, @RequestBody Map<String, List<Integer>> requestBody) {
        if (!jwtUtil.validateToken(token, "company")) {
            return ResponseEntity.status(401).body(null);
        }
        List<Integer> ids = requestBody.get("ids");
        companyService.deleteJobsByList(ids);
        return ResponseEntity.ok().body("success");
    }
}
