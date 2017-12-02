package org.safari.platform.modules.sys.utils;

import org.safari.platform.common.utils.CacheUtil;
import org.safari.platform.modules.sys.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 缓存处理工具类
 * @author Alitalk
 * @date 2017-03-30
 */
public class CacheThread extends Thread {
	
	protected static Logger LOG = LoggerFactory.getLogger(CacheThread.class);
	
	private String cacheType;
	private User user;
	
	public CacheThread(){
		
	}
	
	public CacheThread(String cacheType,User user){
		this.cacheType = cacheType;
		this.user = user;
	}
	
	public void run(){
		LOG.info("==========Thread begin==========");
		if(UserUtil.CACHE_AREA.equals(cacheType)){
			CacheUtil.remove(UserUtil.USER_CACHE_LIST_BY_AREA_ID_ + user.getId());
		}else if(UserUtil.CACHE_ROLE.equals(cacheType)){
			CacheUtil.remove(UserUtil.USER_CACHE_LIST_BY_ROLE_ID_ + user.getId());
		}else if(UserUtil.CACHE_ORG.equals(cacheType)){
			CacheUtil.remove(UserUtil.USER_CACHE_LIST_BY_ORG_ID_+ user.getId());
		}else if(UserUtil.CACHE_PRIVILEGE.equals(cacheType)){
			CacheUtil.remove(UserUtil.USER_CACHE_LIST_BY_PRIVILEGE_ID_ + user.getId());
			CacheUtil.remove(UserUtil.USER_CACHE_TREE_BY_PRIVILEGE_ID_ + user.getId());
			CacheUtil.remove(UserUtil.USER_CACHE_MENU_BY_USER_ID_ + user.getId());
			CacheUtil.remove(UserUtil.USER_CACHE_BAR_BY_USER_ID_ + user.getId());
			CacheUtil.remove(UserUtil.USER_CACHE_LIST_BY_USERBAR_ID_ + user.getId());
			CacheUtil.remove(UserUtil.USER_CACHE_ATTR_BY_USER_ID_ + user.getId());
			CacheUtil.remove(UserUtil.USER_CACHE_LIST_BY_USERATTR_ID_ + user.getId());
		}
		LOG.info("==========Thread end==========");
	}
}
