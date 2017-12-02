package org.safari.platform.modules.sys.service.impl.hibernate;

import java.util.List;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.common.service.BaseHibernateService;
import org.safari.platform.modules.sys.dao.OrgDao;
import org.safari.platform.modules.sys.entity.Org;
import org.safari.platform.modules.sys.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 机构service实现类(Hibernate)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("horgService")
@Transactional(readOnly=true)
public class OrgServiceImpl extends BaseHibernateService<Org, String> implements OrgService{
	
	@Autowired
	private OrgDao orgDao;
	
	@Override
	public HibernateDao<Org, String> getEntityDao() {
		return orgDao;
	}

	@Override
	public int deleteSubParentId(Org org) {
		return 0;
	}

	@Override
	public int updateSubParentId(Org org) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Org> findAllTree(Org org) {
		// TODO Auto-generated method stub
		return null;
	}

}
