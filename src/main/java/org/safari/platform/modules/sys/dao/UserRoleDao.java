package org.safari.platform.modules.sys.dao;

import java.util.List;

import org.hibernate.Query;
import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.modules.sys.entity.UserRole;
import org.springframework.stereotype.Repository;

/**
 * 用户角色DAO
 * @author Alitalk
 * @date 2017-02-15
 */
@Repository
public class UserRoleDao extends HibernateDao<UserRole, String>{

	/**
	 * 删除用户角色
	 * @param userId
	 * @param roleId
	 */
	public void deleteUR(String userId,String roleId){
		String hql="delete sys_user_role ur where ur.user.id=?0 and ur.role.id=?1";
		batchExecute(hql, userId,roleId);
	}
	
	/**
	 * 查询用户拥有的角色id集合
	 * @param userId
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<String> findRoleIds(String userId){
		String hql="select ur.role.id from sys_user_role ur where ur.user.id=?0";
		Query query= createQuery(hql, userId);
		return query.list();
	}
	
}
