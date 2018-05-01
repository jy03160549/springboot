package com.ssm.redpacket.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.ssm.ds.redpacket.entity.UserRedPacket;
import com.ssm.redpacket.mapper.RedPacketMapper;
import com.ssm.redpacket.service.RedisRedPacketService;

public class RedisRedPacketServiceImpl implements RedisRedPacketService{
	
	private static final String PREFIX = "red_packet_list_";
	
	private static final int TIME_SIZE = 1000;
	
	@Autowired
	protected RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private RedPacketMapper redPacketMapper = null;

	@Override
	public void saveUserRedPacketByRedis(Long redPacketId, Double unitAmount) {
		// TODO Auto-generated method stub
		System.out.println("开始保存数据");
		Long start = System.currentTimeMillis();
		
		BoundListOperations ops= redisTemplate.boundListOps(PREFIX+redPacketId);
		
		Long size =ops.size();
		
		Long time = size % TIME_SIZE ==0 ? size / TIME_SIZE : size / TIME_SIZE + 1;
		int count = 0;
		List<UserRedPacket> userRedPacketList=new ArrayList<UserRedPacket>(TIME_SIZE);
		for(int i=0;i<time;i++){
			List userIdList =null;
			if(i==0){
				userIdList=ops.range(i*TIME_SIZE, (i+1)*TIME_SIZE);
				
			}else {
				userIdList=ops.range(i*TIME_SIZE+1, (i+1)*TIME_SIZE);
			}
		}
		userRedPacketList.clear();
		
	}

}
