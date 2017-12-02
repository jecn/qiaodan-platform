package org.safari.platform.modules.sys.service.impl.hibernate;

import java.util.List;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.common.service.BaseHibernateService;
import org.safari.platform.modules.sys.dao.AreaDao;
import org.safari.platform.modules.sys.entity.Area;
import org.safari.platform.modules.sys.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 区域service实现类(Hibernate)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("hareaService")
@Transactional(readOnly=true)
public class AreaServiceImpl extends BaseHibernateService<Area, String> implements AreaService {
	
	@Autowired
	private AreaDao areaDao;

	@Override
	public HibernateDao<Area, String> getEntityDao() {
		return areaDao;
	}

	@Override
	public int deleteSubParentId(Area area) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSubParentId(Area area) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Area> findAllTree(Area area) {
		// TODO Auto-generated method stub
		return null;
	}

}
