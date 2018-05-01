package com.ssm.user.mapper;


import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ssm.ds.user.entity.User;


public interface  UserMapper {
	
	@Select("select * from user where username = #{name}")
	@Results({
        @Result(property = "username",  column = "USERNAME"),
        @Result(property = "password", column = "PASSWORD")
    })
	public User findUserByName(String name);

}
