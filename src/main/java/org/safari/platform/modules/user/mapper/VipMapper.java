package org.safari.platform.modules.user.mapper;

import org.apache.ibatis.annotations.Param;
import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.modules.user.entity.Vip;

@MyBatisMapper
public interface VipMapper extends BaseMapper<Vip> {

	public void resetPassword(@Param("id") String id, @Param("password") String password);

	public void updateStat(Vip vip);
	
	public Long countLimit(String limit);
	
	public Long countTime(String cycle);
	
	public Long countCycle(@Param("beginTime") String beginTime, @Param("endTime") String endTime);
	
	public Long countByAge(@Param("beginAge") String beginAge, @Param("endAge") String endAge);
}
