package org.safari.platform.modules.sys.dao;

import java.util.List;

import org.hibernate.Query;
import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.modules.sys.entity.Log;
import org.springframework.stereotype.Repository;

/**
 * 日志DAO
 * @author Alitalk
 * @date 2017-02-15
 */
@Repository
public class LogDao extends HibernateDao<Log, String>{
	
	/**
	 * 批量删除日志
	 * @param ids 日志id列表
	 */
	public void deleteBatch(List<String> idList){
		String hql="delete from sys_log t where t.id in (:idList)";
		Query query=getSession().createQuery(hql);
		query.setParameterList("idList", idList);
		query.executeUpdate();
	}
}
