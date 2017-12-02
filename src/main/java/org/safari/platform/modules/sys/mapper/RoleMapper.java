package org.safari.platform.modules.sys.mapper;

import java.util.List;

import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.modules.sys.entity.Role;

/**
 * 角色Mapper接口
 * @author Alitalk
 * @date 2017-02-10
 */
@MyBatisMapper
public interface RoleMapper extends BaseMapper<Role> {

	public Role getByName(Role role);
	
	public Role getByEnname(Role role);
	
	/**
	 * 添加角色记录
	 * @param role
	 * @return int
	 */
	public int insertRoleMenu(Role role);

	/**
	 * 维护角色与菜单权限关系
	 * @param role
	 * @return int
	 */
	public int deleteRoleMenu(Role role);
	
	/**
	 * 维护角色与公司部门关系
	 * @param role
	 * @return
	 */
	public int insertRoleOffice(Role role);

	/**
	 * 维护角色与公司部门关系
	 * @param role
	 * @return
	 */
	public int deleteRoleOffice(Role role);

	/**
	 * 获取用户角色
	 * @param role
	 * @return
	 */
	public List<Role> findUserRoles(Role role);

}
