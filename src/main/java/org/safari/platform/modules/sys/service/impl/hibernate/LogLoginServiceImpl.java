package org.safari.platform.modules.sys.service.impl.hibernate;

import java.util.List;

import org.safari.platform.common.dao.HibernateDao;
import org.safari.platform.common.service.BaseHibernateService;
import org.safari.platform.modules.sys.dao.LogLoginDao;
import org.safari.platform.modules.sys.entity.LogLogin;
import org.safari.platform.modules.sys.service.LogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 登录日志service实现类(Hibernate)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("hlogLoginService")
@Transactional(readOnly=true)
public class LogLoginServiceImpl extends BaseHibernateService<LogLogin, String> implements LogLoginService {
	
	@Autowired
	private LogLoginDao logLoginDao;
	
	@Override
	public HibernateDao<LogLogin, String> getEntityDao() {
		return logLoginDao;
	}

	@Override
	public void delete(List<String> idList) {
		
	}

	
}
