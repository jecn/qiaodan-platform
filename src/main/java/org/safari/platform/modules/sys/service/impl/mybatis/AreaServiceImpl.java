package org.safari.platform.modules.sys.service.impl.mybatis;

import org.safari.platform.common.service.TreeService;
import org.safari.platform.modules.sys.entity.Area;
import org.safari.platform.modules.sys.mapper.AreaMapper;
import org.safari.platform.modules.sys.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 区域service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("mareaService")
@Transactional(readOnly=true)
public class AreaServiceImpl extends TreeService<AreaMapper, Area> implements AreaService {
	
	@Autowired
	private AreaMapper areaMapper;

}
