package org.safari.platform.modules.sys.service;

import java.util.List;

import org.safari.platform.common.filter.PropertyFilter;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.sys.entity.Dict;

/**
 * 字典service
 * @author Alitalk
* @date 2017-02-15
 */
public interface DictService {

	public void save(Dict dict);

	public Dict get(String id);

	public void update(Dict dict);

	public void delete(Dict dict);
	
	public PageUtil<Dict> search(PageUtil<Dict> page, List<PropertyFilter> filters);

	public PageUtil<Dict> findPage(PageUtil<Dict> page, Dict dict);

	public Dict findUnique(String key, String type);

	public Dict findByTypeAndValue(String type, String value);

	
}
