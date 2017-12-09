package org.safari.platform.modules.rept.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.modules.rept.entity.s_move;

@MyBatisMapper
public interface s_moveMapper extends BaseMapper<s_move>{
	List<s_move> getUserLocation ();

	List<String> getProvince();

	List<s_move> getUserMoveDataForTime(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
}
