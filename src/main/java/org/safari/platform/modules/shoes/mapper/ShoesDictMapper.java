package org.safari.platform.modules.shoes.mapper;

import org.apache.ibatis.annotations.Param;
import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.modules.shoes.entity.ShoesDict;

@MyBatisMapper
public interface ShoesDictMapper extends BaseMapper<ShoesDict> {

	public ShoesDict findByTypeAndLabel(@Param("type")String type, 
			@Param("label")String label, @Param("delFlag")String delFlag);
	
}
