package com.fancy.internsys.mapper;

import com.fancy.internsys.pojo.userIdentity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userMapper {
    @Insert("INSERT INTO user_basic (UUID,login_mail,password,salt,role) VALUES (#{UUID},#{login_mail},#{password},#{salt},#{role})")
    void addUser(userIdentity user);

    @Select("SELECT UUID,login_mail,password,salt,role FROM user_basic WHERE login_mail = #{login_mail}")
    userIdentity searchUser(String login_mail);
}
