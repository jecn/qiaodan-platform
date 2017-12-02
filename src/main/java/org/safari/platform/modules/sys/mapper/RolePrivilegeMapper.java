package org.safari.platform.modules.sys.mapper;

import java.util.List;

import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.modules.sys.entity.Role;
import org.safari.platform.modules.sys.entity.RolePrivilege;

/**
 * 角色权限Mapper接口
 * @author Alitalk
 * @date 2017-02-10
 */
@MyBatisMapper
public interface RolePrivilegeMapper extends BaseMapper<RolePrivilege> {

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
	 * 获取权限ID
	 * @param roleId
	 * @return
	 */
	public List<String> findByRoleId(String roleId);

	/**
	 * 删除角色对应的权限
	 * @param roleId
	 */
	public void deleteByRoleId(String roleId);
	
	/**
	 * 获取权限ID
	 * @param privilegeId
	 * @return
	 */
	public List<String> findByPrivilegeId(String privilegeId);

	/**
	 * 删除角色对应的权限
	 * @param privilegeId
	 */
	public void deleteByPrivilegeId(String privilegeId);

	

}
