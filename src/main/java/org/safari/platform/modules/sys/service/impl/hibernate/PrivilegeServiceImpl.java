package org.safari.platform.modules.sys.service.impl.hibernate;

import java.util.List;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.common.service.BaseHibernateService;
import org.safari.platform.modules.sys.dao.PrivilegeDao;
import org.safari.platform.modules.sys.entity.Privilege;
import org.safari.platform.modules.sys.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限service实现类(Hibernate)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("hprivilegeService")
@Transactional(readOnly=true)
public class PrivilegeServiceImpl extends BaseHibernateService<Privilege, String> implements PrivilegeService{
	
	@Autowired
	private PrivilegeDao privilegeDao;
	
	@Override
	public HibernateDao<Privilege, String> getEntityDao() {
		return privilegeDao;
	}

	@Override
	public List<Privilege> getMenus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Privilege> getMenuOperation(String pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Privilege> findByUserId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBaseOpe(String pid, String pname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Privilege> findSubByParentId(Privilege privilege) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Privilege> findTopMenu(Privilege privilege) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteSubParentId(Privilege privilege) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSubParentId(Privilege privilege) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Privilege findByPidAndName(String pid, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Privilege findUnique(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Privilege> findAllTree(Privilege privilege) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
