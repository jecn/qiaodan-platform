package org.safari.platform.modules.sys.service;

import org.safari.platform.common.utils.PageUtil;
import org.safari.platform.modules.sys.entity.I18N;

/**
 * 角色service
 * @author Alitalk
 * @date 2017-02-15
 */
public interface I18NService {

	/**
	 * 保存
	 * @date 2016-5-23
	 * @param i18n I18N
	 */
	public void save(I18N i18n);
	
	/**
	 * 根据主键查找记录
	 * @date 2016-5-23
	 * @param id String
	 * @return I18N
	 */
	public I18N get(String id);
	
	
	/**
	 * 单条删除记录(逻辑删除)
	 * @date 2016-5-23
	 * @param i18n I18N
	 */
	public void delete(I18N i18n);
	
	/**
	 * 分页查询、搜索
	 * @date 2016-5-23
	 * @param i18n I18N
	 * @return Page<I18N>
	 */
	public PageUtil<I18N> findPage(PageUtil<I18N> page, I18N i18n);
	
	/**
	 * 初始化语言信息，服务启动时直接加入到内存中
	 */
	public void initI18N();
	
	/**
	 * 获取特定语言下的值
	 * @param langKey String 
	 * @return String
	 */
	public String getLangValue(String langKey);
	
	/**
	 * 获取特定语言下的值
	 * @param langKey String  
	 * @param langArg String  
	 * @return String
	 */
	public String getLangValue(String langKey, String langArg);
	
	/**
	 * 刷新缓存
	 * @date 2016-5-23
	 */
	public void reflushCache();
	
	/**
	 * 更新缓存，添加缓存记录
	 * @date 2016-5-23
	 * @param i18n I18N
	 */
	public void put(I18N i18n);
	
	/**
	 * 更新缓存，修改缓存记录
	 * @param langCode String
	 * @param langKey String
	 * @param langValue String
	 */
	public void motify(String langCode,String langKey,String langValue);
	
	/**
	 * 更新缓存，修改缓存记录
	 * @date 2016-5-23
	 * @param i18n I18N
	 */
	public void motify(I18N i18n);
	
	/**
	 * 更新缓存，添加缓存记录
	 * @date 2016-5-23
	 * @param langCode String
	 * @param langKey String
	 * @param langValue String
	 */
	public void put(String langCode,String langKey,String langValue);
	
	/**
	 * 更新缓存，移除缓存记录
	 * @date 2016-5-23
	 * @param i18n I18N
	 */
	public void remove(I18N i18n);
	
	/**
	 * 更新缓存，移除缓存记录
	 * @date 2016-5-23
	 * @param langCode String
	 * @param langKey String
	 */
	public void remove(String langCode,String langKey);

	/**
	 * 获取语言编码下key的值
	 * @param langCode
	 * @param langKey
	 * @return
	 */
	public I18N findByCodeAndKey(String langCode, String langKey);

}
