package org.safari.platform.modules.sys.dao;

import java.util.List;

import org.hibernate.Query;
import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.modules.sys.entity.RolePrivilege;
import org.springframework.stereotype.Repository;

/**
 * 角色权限DAO
 * @author Alitalk
 * @date 2017-02-15
 */
@Repository
public class RolePrivilegeDao extends HibernateDao<RolePrivilege, String>{
	
	/**
	 * 查询角色拥有的权限id
	 * @param roleId
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<String> findPrivilegeIds(String roleId){
		String hql="select rp.privilege.id from sys_role_privilege rp where rp.role.id=?0";
		Query query= createQuery(hql, roleId);
		return query.list();
	}

	/**
	 * 删除角色权限
	 * @param roleId
	 * @param privilegeId
	 */
	public void deleteRP(String roleId,String privilegeId){
		String hql="delete sys_role_privilege rp where rp.role.id=?0 and rp.privilege.id=?1";
		batchExecute(hql, roleId,privilegeId);
	}
	
}
