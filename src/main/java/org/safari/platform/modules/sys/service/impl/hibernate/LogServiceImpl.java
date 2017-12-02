package org.safari.platform.modules.sys.service.impl.hibernate;

import java.util.List;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.common.filter.PropertyFilter;
import org.safari.platform.common.service.BaseHibernateService;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.sys.dao.LogDao;
import org.safari.platform.modules.sys.entity.Log;
import org.safari.platform.modules.sys.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日志service实现类(Hibernate)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("hlogService")
@Transactional(readOnly=true)
public class LogServiceImpl extends BaseHibernateService<Log, String> implements LogService {
	
	@Autowired
	private LogDao logDao;
	
	@Override
	public HibernateDao<Log, String> getEntityDao() {
		return logDao;
	}

	@Override
	public void delete(List<String> idList) {
		
	}

	@Override
	public PageUtil<Log> search(PageUtil<Log> logPage,
			List<PropertyFilter> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageUtil<Log> findPage(PageUtil<Log> page, Log log) {
		return null;
	}

	
}
