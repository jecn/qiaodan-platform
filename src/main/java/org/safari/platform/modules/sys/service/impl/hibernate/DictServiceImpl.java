package org.safari.platform.modules.sys.service.impl.hibernate;

import java.util.List;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.common.filter.PropertyFilter;
import org.safari.platform.common.service.BaseHibernateService;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.sys.dao.DictDao;
import org.safari.platform.modules.sys.entity.Dict;
import org.safari.platform.modules.sys.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典service实现类(Hibernate)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("hdictService")
@Transactional(readOnly=true)
public class DictServiceImpl extends BaseHibernateService<Dict, String> implements DictService {
	
	@Autowired
	private DictDao dictDao;

	@Override
	public HibernateDao<Dict, String> getEntityDao() {
		return dictDao;
	}

	@Override
	public PageUtil<Dict> search(PageUtil<Dict> page,
			List<PropertyFilter> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageUtil<Dict> findPage(PageUtil<Dict> page, Dict dict) {
		return null;
	}

	@Override
	public Dict findUnique(String key, String type) {
		return null;
	}

	@Override
	public Dict findByTypeAndValue(String type, String value) {
		// TODO Auto-generated method stub
		return null;
	}
}
