package org.safari.platform.modules.sys.service.impl.hibernate;

import java.util.List;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.common.service.BaseHibernateService;
import org.safari.platform.modules.sys.entity.UserRole;
import org.safari.platform.modules.sys.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户service实现类(Hibernate)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("huserRoleService")
@Transactional(readOnly = true)
public class UserRoleServiceImpl extends BaseHibernateService<UserRole, String> implements UserRoleService{

	@Override
	public HibernateDao<UserRole, String> getEntityDao() {
		return null;
	}

	@Override
	public List<String> getRoleIdList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUserRole(String userId, List<String> roleList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getUserIdList(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
