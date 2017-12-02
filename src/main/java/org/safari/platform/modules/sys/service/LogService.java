package org.safari.platform.modules.sys.service;

import java.util.List;

import org.safari.platform.common.filter.PropertyFilter;
import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.sys.entity.Log;

/**
 * 日志service
 * @author Alitalk
 * @date 2017-02-15
 */
public interface LogService {
	
	/**
	 * 保存日志
	 * @param log
	 */
	public void save(Log log);
	
	/**
	 * 批量删除日志
	 * @param idList
	 */
	public void delete(List<String> idList);

	/**
	 * 删除日志
	 * @param log
	 */
	public void delete(Log log);
	
	/**
	 * 搜索查询日志
	 * @param logPage
	 * @param filters
	 * @return
	 */
	public PageUtil<Log> search(PageUtil<Log> logPage, List<PropertyFilter> filters);

	public PageUtil<Log> findPage(PageUtil<Log> page, Log log);
	

}
