package org.safari.platform.modules.sys.service;

import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.sys.entity.Corp;

/**
 * 单位service
 * @author Alitalk
* @date 2017-03-30
 */
public interface CorpService {

	public Corp get(String id);

	public PageUtil<Corp> findPage(PageUtil<Corp> page, Corp corp);

	public void save(Corp corp);
	
	public void delete(Corp corp);

	public Corp findUnique(String key, String value);
}
