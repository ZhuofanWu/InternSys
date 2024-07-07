package com.fancy.internsys.mapper;

import com.fancy.internsys.pojo.UserIdentity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user_basic (UUID,login_mail,password,role) VALUES (#{UUID},#{login_mail},#{password},#{role})")
    void addUser(UserIdentity user);

    @Select("SELECT UUID,login_mail,password,role FROM user_basic WHERE login_mail = #{login_mail}")
    UserIdentity searchUser(String login_mail);
}
