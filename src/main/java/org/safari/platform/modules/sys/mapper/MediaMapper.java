package org.safari.platform.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.modules.sys.entity.Media;

/**
 * 媒体Mapper接口
 * @author Alitalk
 * @date 2017-02-10
 */
@MyBatisMapper
public interface MediaMapper {
	
	public Media get(String id);
	
	public int deleteById(String id);

	public List<Media> findByIds(@Param("idList")String[] idList);
	
	public int insert(Media media);
	
}
