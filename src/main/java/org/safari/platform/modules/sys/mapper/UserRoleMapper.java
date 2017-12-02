package org.safari.platform.modules.sys.mapper;

import java.util.List;

import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.modules.sys.entity.UserRole;

/**
 * 用户角色Mapper接口
 * @author Alitalk
 * @date 2017-02-10
 */
@MyBatisMapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

	/**
	 * 查找用户的角色
	 * @param userId
	 * @return
	 */
	public List<String> findByUserId(String userId);

	/**
	 * 删除用户的角色
	 * @param userId
	 */
	public void deleteByUserId(String userId);

	/**
	 * 查找角色下的用户
	 * @param roleId
	 * @return
	 */
	public List<String> findByRoleId(String roleId);
	
	/**
	 * 删除角色下的用户
	 * @param roleId
	 */
	public void deleteByRoleId(String roleId);
	
}
