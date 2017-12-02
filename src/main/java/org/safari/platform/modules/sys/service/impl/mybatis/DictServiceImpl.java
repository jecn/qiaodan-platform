package org.safari.platform.modules.sys.service.impl.mybatis;

import java.util.List;

import org.safari.platform.common.config.Global;
import org.safari.platform.common.filter.PropertyFilter;
import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.sys.entity.Dict;
import org.safari.platform.modules.sys.mapper.DictMapper;
import org.safari.platform.modules.sys.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("mdictService")
@Transactional(readOnly=true)
public class DictServiceImpl extends BaseMybatisService<DictMapper, Dict> implements DictService {

	@Autowired
	private DictMapper dictMapper;
	
	@Override
	public PageUtil<Dict> search(PageUtil<Dict> page, List<PropertyFilter> filters) {
		return null;
	}

	@Override
	public Dict findByTypeAndValue(String type, String value) {
		return dictMapper.findByTypeAndValue(type,value,Global.DEL_NO);
	}

}
