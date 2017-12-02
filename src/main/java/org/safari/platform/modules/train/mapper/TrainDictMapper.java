package org.safari.platform.modules.train.mapper;

import org.apache.ibatis.annotations.Param;
import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.modules.train.entity.TrainDict;

@MyBatisMapper
public interface TrainDictMapper extends BaseMapper<TrainDict> {

	public TrainDict findByNameAndType(@Param("name")String name, 
			@Param("type")String type, @Param("delFlag")String delFlag);

	
}
