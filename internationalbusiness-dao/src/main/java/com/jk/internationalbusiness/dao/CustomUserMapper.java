package com.jk.internationalbusiness.dao;


import com.jk.internationalbusiness.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomUserMapper {

    //@Select("select user_name from tbl_user_info where user_name = #{userName}")
    User findUserByUsername(String loginName);
}
