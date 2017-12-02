package org.safari.platform.modules.sys.service.impl.hibernate;

import java.util.List;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.common.service.BaseHibernateService;
import org.safari.platform.modules.sys.entity.UserOrg;
import org.safari.platform.modules.sys.service.UserOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户service实现类(Hibernate)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("huserOrgService")
@Transactional(readOnly = true)
public class UserOrgServiceImpl extends BaseHibernateService<UserOrg, String> implements UserOrgService{

	@Override
	public HibernateDao<UserOrg, String> getEntityDao() {
		return null;
	}

	@Override
	public List<String> getOrgIdList(String userId) {
		return null;
	}

	@Override
	public void updateUserOrg(String userId, List<String> orgList) {
		
	}
	

}
