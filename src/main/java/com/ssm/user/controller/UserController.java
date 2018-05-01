package com.ssm.user.controller;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssm.ds.user.entity.User;
import com.ssm.user.mapper.UserMapper;

@RestController
@RequestMapping({"/user"})
public class UserController {

	@Autowired
	UserMapper userMapper;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@RequestMapping(value = "/getUser")
	@ResponseBody
    public String getUser() {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
		User user=userMapper.findUserByName("admin");
		if(null != user){
			return "Hello "+user.getUsername()+"  "+user.getPassword();
		}
		
        return "Hello";
    }

}
