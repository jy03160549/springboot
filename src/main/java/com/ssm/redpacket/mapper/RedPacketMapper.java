package com.ssm.redpacket.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssm.ds.redpacket.entity.RedPacket;

public interface RedPacketMapper {
	
	@Select("select ID,USER_ID AS USERID,AMOUNT,SEND_DATE AS SENDDATE,TOTAL,UNIT_AMOUNT AS UNITAMOUNT,STOCK,VERSION,NOTE from T_RED_packet where ID = #{id}")
	@Results({
        @Result(property = "id",  column = "ID"),
        @Result(property = "userID", column = "USERID"),
        @Result(property = "amount", column = "AMOUNT"),
        @Result(property = "sendDate", column = "SENDDATE"),
        @Result(property = "total", column = "TOTAL"),
        @Result(property = "unitAmount", column = "UNITAMOUNT"),
        @Result(property = "stock", column = "STOCK"),
        @Result(property = "version", column = "VERSION"),
        @Result(property = "note", column = "NOTE")
    })
	public RedPacket getRedPacket(Long id);
	
	@Select("select ID,USER_ID AS USERID,AMOUNT,SEND_DATE AS SENDDATE,TOTAL,UNIT_AMOUNT AS UNITAMOUNT,STOCK,VERSION,NOTE from T_RED_packet where ID = #{id} for update")
	@Results({
        @Result(property = "id",  column = "ID"),
        @Result(property = "userID", column = "USERID"),
        @Result(property = "amount", column = "AMOUNT"),
        @Result(property = "sendDate", column = "SENDDATE"),
        @Result(property = "total", column = "TOTAL"),
        @Result(property = "unitAmount", column = "UNITAMOUNT"),
        @Result(property = "stock", column = "STOCK"),
        @Result(property = "version", column = "VERSION"),
        @Result(property = "note", column = "NOTE")
    })
	public RedPacket getRedPacketForUpdate(Long id);
	
	
	@Update("update T_RED_packet set STOCK = STOCK -1   where ID = #{id}")
	public int decreaseRedPacket(Long id);
	
	@Update("update T_RED_packet set STOCK = STOCK -1, VERSION = VERSION + 1   where ID = #{0} and VERSION = #{1} ")
	public int decreaseRedPacketForVersion(Long id,Integer version);

}
