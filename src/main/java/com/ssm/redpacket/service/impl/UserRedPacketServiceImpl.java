package com.ssm.redpacket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.ds.redpacket.entity.RedPacket;
import com.ssm.ds.redpacket.entity.UserRedPacket;
import com.ssm.redpacket.dao.RedPacketDao;
import com.ssm.redpacket.dao.UserRedPacketDao;
import com.ssm.redpacket.mapper.RedPacketMapper;
import com.ssm.redpacket.mapper.grapRedPacketMapper;
import com.ssm.redpacket.service.UserRedPacketService;

@Service
public class UserRedPacketServiceImpl implements UserRedPacketService{

	@Autowired
	private grapRedPacketMapper grapRedPacketMapper = null;
	
	@Autowired
	private RedPacketMapper redPacketMapper = null;
	
	private static final int FAILED = 0;
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	public int grapRedPacket(Long redPacketId, Long userId) {
		RedPacket redPacket= redPacketMapper.getRedPacket(redPacketId);
		if(null != redPacket && redPacket.getStock() >0 ) {
			redPacketMapper.decreaseRedPacket(redPacketId);
			UserRedPacket userRedPacket =new UserRedPacket();
			userRedPacket.setRedPacketId(redPacketId);
			userRedPacket.setUserId(userId);
			userRedPacket.setAmount(redPacket.getUnitAmount());
			userRedPacket.setNote("抢红包"+redPacketId);
			int result = grapRedPacketMapper.grapRedPacket(userRedPacket);
			return result;
			
			
		}
		return FAILED;
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	public int grapRedPacketForVersion(Long redPacketId, Long userId) {
		for(int i=0;i<3;i++){
			RedPacket redPacket= redPacketMapper.getRedPacket(redPacketId);
			if(null != redPacket && redPacket.getStock() >0 ) {
				int update=redPacketMapper.decreaseRedPacketForVersion(redPacketId,redPacket.getVersion());
				if (update == 0){
					continue;
				}
				UserRedPacket userRedPacket =new UserRedPacket();
				userRedPacket.setRedPacketId(redPacketId);
				userRedPacket.setUserId(userId);
				userRedPacket.setAmount(redPacket.getUnitAmount());
				userRedPacket.setNote("抢红包"+redPacketId);
				int result = grapRedPacketMapper.grapRedPacket(userRedPacket);
				return result;
				
				
			}
		}

		return FAILED;
		
	}

}
