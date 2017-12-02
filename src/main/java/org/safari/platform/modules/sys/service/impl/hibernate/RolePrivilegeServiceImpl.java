package org.safari.platform.modules.sys.service.impl.hibernate;

import java.util.List;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.common.service.BaseHibernateService;
import org.safari.platform.modules.sys.entity.RolePrivilege;
import org.safari.platform.modules.sys.service.RolePrivilegeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色service实现类(Hibernate)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("hrolePrivilegeService")
@Transactional(readOnly = true)
public class RolePrivilegeServiceImpl extends BaseHibernateService<RolePrivilege, String> implements RolePrivilegeService{

	@Override
	public HibernateDao<RolePrivilege, String> getEntityDao() {
		return null;
	}

	@Override
	public List<String> getPrivilegeIdsByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRolePrivilege(String roleId, List<String> privilegeList) {
		// TODO Auto-generated method stub
		
	}


}
