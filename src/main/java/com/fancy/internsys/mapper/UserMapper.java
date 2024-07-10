package com.fancy.internsys.mapper;

import com.fancy.internsys.pojo.UserIdentity;
import com.fancy.internsys.pojo.UserPasswordReset;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user_basic (UUID,login_mail,password,role) VALUES (#{UUID},#{login_mail},#{password},#{role})")
    void addUser(UserIdentity user);

    @Select("SELECT UUID,login_mail,password,role FROM user_basic WHERE login_mail = #{login_mail}")
    UserIdentity searchUser(String login_mail);

    @Update("UPDATE user_basic SET password=#{password} WHERE login_mail = #{login_mail}")
    void changePasswordUser(@Param("login_mail") String login_mail,@Param("password") String password);

    @Delete("DELETE from user_basic where login_mail=#{login_mail}")
    void deleteUser(String login_mail);

    @Insert("INSERT INTO user_reset_token (uuid, login_mail, token, expire_time) VALUES (#{uuid},#{login_mail},#{token},#{expire_time})")
    void insertToken(UserPasswordReset userPasswordReset);

    @Select("SELECT uuid,login_mail,token,expire_time from user_reset_token where login_mail=#{login_mail}")
    List<UserPasswordReset> validateToken(String login_mail);

    @Select("SELECT * FROM user_reset_token")
    List<UserPasswordReset> searchTokens();

    @Delete("DELETE from user_reset_token where uuid=#{uuid}")
    void deleteExpiredToken(String uuid);

}
