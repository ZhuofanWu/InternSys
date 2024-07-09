package com.fancy.internsys.mapper;

import com.fancy.internsys.pojo.UserIdentity;
import com.fancy.internsys.pojo.UserPasswordReset;
import org.apache.ibatis.annotations.*;

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

    @Insert("INSERT INTO user_reset_token (login_mail, token, expire_time) VALUES (#{login_mail},#{token},#{expire_time})")
    void insertToken(UserPasswordReset userPasswordReset);
}
