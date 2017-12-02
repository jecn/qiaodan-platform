package org.safari.platform.modules.sys.service.impl.mybatis;

import java.util.List;

import org.safari.platform.common.filter.PropertyFilter;
import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.sys.entity.Role;
import org.safari.platform.modules.sys.mapper.RoleMapper;
import org.safari.platform.modules.sys.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("mroleService")
@Transactional(readOnly=true)
public class RoleServiceImpl extends BaseMybatisService<RoleMapper, Role> implements RoleService {

	@Override
	public PageUtil<Role> search(PageUtil<Role> page,
			List<PropertyFilter> filters) {
		return null;
	}


}
