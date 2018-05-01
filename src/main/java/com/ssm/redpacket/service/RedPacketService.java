package com.ssm.redpacket.service;

import com.ssm.ds.redpacket.entity.RedPacket;

public interface RedPacketService {

	public RedPacket getRedPacket(Long id);
	
	public int decreaseRedPacket(Long id);
}
