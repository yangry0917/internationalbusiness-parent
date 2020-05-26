package com.jk.internationalbusiness.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface HelloMapper {

    List<Map<String,String>> hello();

    @Select("select ID,NAME from test where id = #{id}")
    List<Map<String,String>> helloWorld(String id);
}
