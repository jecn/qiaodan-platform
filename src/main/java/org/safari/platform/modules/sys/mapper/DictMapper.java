package org.safari.platform.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.modules.sys.entity.Dict;

/**
 * 字典Mapper接口
 * @author Alitalk
 * @date 2017-02-10
 */
@MyBatisMapper
public interface DictMapper extends BaseMapper<Dict> {

	/**
	 * 通过类型查找
	 * @param dict
	 * @return List<Dict>
	 */
	public List<Dict> findByType(Dict dict);
	
	/**
	 * 通过类型和标签/值查找
	 * @param dict
	 * @return Dict
	 */
	public Dict findByTypeAndLabelOrValue(Dict dict);

	/**
	 * 通过类型和值查找
	 * @param type
	 * @param value
	 * @param delFlag
	 * @return
	 */
	public Dict findByTypeAndValue(@Param("type")String type, @Param("value")String value,@Param("delFlag") String delFlag);
}
