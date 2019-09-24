package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modify,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModify},#{avatarUrl})")
    public void insert(User user);

    @Select("select * from user where token=#{token}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "accountId",column = "account_id"),
            @Result(property = "token",column = "token"),
            @Result(property = "gmtCreate",column = "gmt_Create"),
            @Result(property = "gmtModify",column = "gmt_Modify"),
            @Result(property = "avatarUrl",column = "avatar_url"),

    })
    User findByToken(String token);

    @Select("select * from user where token=#{}")
    User findByAccountId(Integer creator);
}
