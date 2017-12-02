package org.safari.platform.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.TreeMapper;
import org.safari.platform.modules.sys.entity.Privilege;

/**
 * 菜单Mapper接口
 * @author Alitalk
 * @date 2017-02-10
 */
@MyBatisMapper
public interface PrivilegeMapper extends TreeMapper<Privilege> {

	public List<Privilege> findByUser(Privilege privilege);
	
	public int updateParentIds(Privilege privilege);
	
	public int updateSort(Privilege privilege);

	public List<Privilege> findByParentId(Privilege privilege);
	
	public List<Privilege> findMenuByUser(Privilege privilege);

	public List<Privilege> findFunByUser(Privilege privilege);

	public List<Privilege> findAllTree(Privilege privilege);

	public List<Privilege> findTree(Privilege privilege);

	public List<Privilege> findTopMenu(Privilege privilege);

	public Privilege findByPidAndName(@Param("pid")String pid, 
			@Param("name")String name, @Param("delFlag")String delFlag);
}
