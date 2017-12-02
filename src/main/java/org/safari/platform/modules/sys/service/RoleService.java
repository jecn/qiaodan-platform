package org.safari.platform.modules.sys.service;

import java.util.List;

import org.safari.platform.common.filter.PropertyFilter;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.sys.entity.Role;

/**
 * 角色service
 * @author Alitalk
 * @date 2017-02-15
 */
public interface RoleService {

	public PageUtil<Role> search(PageUtil<Role> page, List<PropertyFilter> filters);

	public void save(Role role);

	public Role get(String id);

	public void delete(Role role);

	public PageUtil<Role> findPage(PageUtil<Role> page, Role role);

	public Role findUnique(String key, String value);

}
