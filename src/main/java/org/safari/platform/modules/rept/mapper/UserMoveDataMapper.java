package org.safari.platform.modules.rept.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.modules.rept.entity.UserMoveData;
@MyBatisMapper
public interface UserMoveDataMapper extends BaseMapper<UserMoveData>{


	List<UserMoveData> getUserMoveDataForTime(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
	

}
