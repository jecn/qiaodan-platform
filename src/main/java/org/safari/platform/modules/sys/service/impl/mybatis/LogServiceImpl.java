package org.safari.platform.modules.sys.service.impl.mybatis;

import java.util.List;

import org.safari.platform.common.filter.PropertyFilter;
import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.sys.entity.Log;
import org.safari.platform.modules.sys.mapper.LogMapper;
import org.safari.platform.modules.sys.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日志service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("mlogService")
@Transactional(readOnly=true)
public class LogServiceImpl extends BaseMybatisService<LogMapper, Log> implements LogService {

	@Override
	public PageUtil<Log> search(PageUtil<Log> logPage,
			List<PropertyFilter> filters) {
		// TODO Auto-generated method stub
		return null;
	}


}
