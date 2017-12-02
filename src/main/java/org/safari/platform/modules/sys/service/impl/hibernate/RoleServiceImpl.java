package org.safari.platform.modules.sys.service.impl.hibernate;

import java.util.List;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.common.filter.PropertyFilter;
import org.safari.platform.common.service.BaseHibernateService;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.sys.dao.RoleDao;
import org.safari.platform.modules.sys.entity.Role;
import org.safari.platform.modules.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色service实现类(Hibernate)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("hroleService")
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseHibernateService<Role, String> implements RoleService{

	@Autowired
	private RoleDao roleDao;

	@Override
	public HibernateDao<Role, String> getEntityDao() {
		return roleDao;
	}

	@Override
	public PageUtil<Role> search(PageUtil<Role> page,
			List<PropertyFilter> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageUtil<Role> findPage(PageUtil<Role> page, Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findUnique(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}
}
