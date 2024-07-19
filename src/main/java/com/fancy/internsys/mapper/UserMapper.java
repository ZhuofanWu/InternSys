package com.fancy.internsys.mapper;

import com.fancy.internsys.pojo.UserIdentity;
import com.fancy.internsys.pojo.UserPasswordReset;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void addUser(UserIdentity user);

    UserIdentity searchUser(String login_mail);

    String getUUID(String login_mail);

    void changePasswordUser(String login_mail, String password);

    void deleteUser(String login_mail);

    void insertToken(UserPasswordReset userPasswordReset);

    List<UserPasswordReset> validateToken(String login_mail);

    List<UserPasswordReset> searchTokens();

    void deleteExpiredToken(String uuid);
}
