package com.ssm.redpacket.dao;

import com.ssm.ds.redpacket.entity.RedPacket;

public interface RedPacketDao {
	
	public RedPacket getRedPacket(Long id);
	
	public int decreaseRedPacket(Long id);

}
