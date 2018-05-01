package com.ssm.redpacket.mapper;

import org.apache.ibatis.annotations.Insert;

import com.ssm.ds.redpacket.entity.UserRedPacket;

public interface grapRedPacketMapper {
	
	@Insert("insert into T_USER_RED_PACKET( RED_PACKET_ID,USER_ID,AMOUNT,GRAB_TIME,NOTE ) values (#{redPacketId},#{userId},#{amount},now(),#{note}  )  ")
	public int grapRedPacket(UserRedPacket userRedPacket);

}
