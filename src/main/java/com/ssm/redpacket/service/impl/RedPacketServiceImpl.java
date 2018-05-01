package com.ssm.redpacket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.ds.redpacket.entity.RedPacket;
import com.ssm.redpacket.dao.RedPacketDao;
import com.ssm.redpacket.mapper.RedPacketMapper;
import com.ssm.redpacket.service.RedPacketService;

@Service
public class RedPacketServiceImpl implements RedPacketService{
	
	@Autowired
	private RedPacketMapper redPacketMapper =null;

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public RedPacket getRedPacket(Long id) {
		// TODO Auto-generated method stub
		return redPacketMapper.getRedPacket(id);
	}

	@Override
	public int decreaseRedPacket(Long id) {
		// TODO Auto-generated method stub
		return redPacketMapper.decreaseRedPacket(id);
	}

}
