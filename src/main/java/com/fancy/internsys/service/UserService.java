package com.fancy.internsys.service;

import com.fancy.internsys.mapper.UserInfoMapper;
import com.fancy.internsys.mapper.UserMapper;
import com.fancy.internsys.pojo.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserInfoMapper userInfoMapper;

    public boolean registerUser(String email,String rawPassword,String role){
        UserIdentity newUser;
        newUser = userMapper.searchUser(email);
        if(newUser!= null){ //如果存在这个用户，拒绝再次注册
            return false;
        }
        newUser=new UserIdentity();
        String uuid= UUID.randomUUID().toString();
        newUser.setUUID(uuid);
        newUser.setLogin_mail(email);
        newUser.setPassword(passwordService.encodePassword(rawPassword));
        newUser.setRole(role);
        userMapper.addUser(newUser);
        initUserInfo(uuid,role); //将用户按照权限初始化，uuid同时设置。
        return true;
    }

    public boolean validateUser(String email,String rawPassword,String role){
        UserIdentity loginUser = userMapper.searchUser(email);
        if(loginUser==null){ //不存在用户
            return false;
        }
        //验证密码和身份
        return passwordService.checkPassword(rawPassword, loginUser.getPassword()) && Objects.equals(role, loginUser.getRole());
    }

    public boolean changePasswordUser(String email,String oldPassword,String newPassword){
        UserIdentity loginUser = userMapper.searchUser(email);
        if(loginUser==null){
            return false;
        }
        if(!passwordService.checkPassword(oldPassword, loginUser.getPassword())){ //不做role的鉴别 放在controller里面
            return false;
        } else {
            userMapper.changePasswordUser(email,newPassword);
            return true;
        }
    }

    public void preResetPasswordUser(String email) throws MessagingException {
        String token = passwordService.generateRandomToken(6);
        // 获取当前时间
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        // 创建Calendar实例并添加2小时
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimestamp.getTime());
        calendar.add(Calendar.HOUR, 2);
        // 获取当前时间加2小时的时间戳
        Timestamp newTimestamp = new Timestamp(calendar.getTimeInMillis());
        //调用mapper写入
        UserPasswordReset user = new UserPasswordReset();
        String uuid= UUID.randomUUID().toString();
        user.setUuid(uuid);
        user.setLogin_mail(email);
        user.setToken(token);
        user.setExpire_time(newTimestamp);
        userMapper.insertToken(user);
        //发email
        try{
            emailService.sendMail(email,"您的密码重置验证码",emailService.resetPasswordEmailPre(email,token),true);
        }catch (MessagingException e){
            throw new MessagingException("邮件失败",e);
        }
    }

    public boolean searchUser(String email){
        UserIdentity user = userMapper.searchUser(email);
        return user != null;
    }

    public boolean resetPassword(String email,String token,String password){
        List<UserPasswordReset> users = userMapper.validateToken(email);
        for(UserPasswordReset user : users){
            if(user.getToken().equals(token)){
                Timestamp expire_time = user.getExpire_time();
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                if(!currentTimestamp.after(expire_time)){
                    userMapper.changePasswordUser(email,passwordService.encodePassword(password));
                    userMapper.deleteExpiredToken(user.getUuid());
                    return true;
                }
            }
        }
        return false;
    }

    public void initUserInfo(String uuid,String role){
        switch (role){
            case "student":{
                StudentInfo studentInfo = new StudentInfo(0,"","default-name","none","2022090901011",1990,"major","1001010","default@email.com","");
                studentInfo.setUuid(uuid);
                userInfoMapper.insertStudent(studentInfo);
                break;
            }
            case "teacher":{
                TeacherInfo teacherInfo = new TeacherInfo(0,"","default-name","10001010","default@email.com");
                teacherInfo.setUuid(uuid);
                userInfoMapper.insertTeacher(teacherInfo);
                break;
            }
            case "sysadmin":{
                SysadminInfo sysadminInfo = new SysadminInfo(0,"","default-name","SISE","100","default@email.com");
                sysadminInfo.setUuid(uuid);
                userInfoMapper.insertSysAdmin(sysadminInfo);
                break;
            }
            case "company":{
                CompanyInfo companyInfo = new CompanyInfo(0,"","default-name","UB","100","default@email.com");
                companyInfo.setUuid(uuid);
                userInfoMapper.insertCompany(companyInfo);
                break;
            }
            default:break;
        }
    }

    public String getUserUUID(String email){
        return userMapper.getUUID(email);
    }
}
