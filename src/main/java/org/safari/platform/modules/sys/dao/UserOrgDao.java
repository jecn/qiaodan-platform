package org.safari.platform.modules.sys.dao;

import java.util.List;

import org.hibernate.Query;
import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.modules.sys.entity.UserOrg;
import org.springframework.stereotype.Repository;

/**
 * 用户机构Dao
 * @author Alitalk
 * @date 2017-02-15
 */
@Repository
public class UserOrgDao extends HibernateDao<UserOrg, String>{

	/**
	 * 删除用户机构
	 * @param userId
	 * @param orgId
	 */
	public void deleteUO(String userId){
		String hql="delete sys_user_org ur where ur.userId=?0 ";
		batchExecute(hql, userId);
	}
	
	/**
	 * 查询用户拥有的机构id集合
	 * @param userId
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public List<String> findOrgIds(String userId){
		String hql="select ur.orgId from sys_user_org ur where ur.userId=?0";
		Query query= createQuery(hql, userId);
		return query.list();
	}
	
}
