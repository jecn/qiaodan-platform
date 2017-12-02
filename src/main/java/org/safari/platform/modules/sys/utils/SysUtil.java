package org.safari.platform.modules.sys.utils;

import java.util.List;

import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.CacheUtil;
import org.safari.platform.common.utils.SpringContextHolderUtil;
import org.safari.platform.modules.sys.entity.Area;
import org.safari.platform.modules.sys.entity.Corp;
import org.safari.platform.modules.sys.entity.Org;
import org.safari.platform.modules.sys.entity.Privilege;
import org.safari.platform.modules.sys.entity.Role;
import org.safari.platform.modules.sys.mapper.AreaMapper;
import org.safari.platform.modules.sys.mapper.CorpMapper;
import org.safari.platform.modules.sys.mapper.OrgMapper;
import org.safari.platform.modules.sys.mapper.PrivilegeMapper;
import org.safari.platform.modules.sys.mapper.RoleMapper;

/**
 * 系统模块工具类
 * @author Alitalk
 * @date 2017-02-15
 */
public class SysUtil {

	private static AreaMapper areaMapper = SpringContextHolderUtil.getBean(AreaMapper.class);
	private static RoleMapper roleMapper = SpringContextHolderUtil.getBean(RoleMapper.class);
	private static OrgMapper orgMapper = SpringContextHolderUtil.getBean(OrgMapper.class);
	private static CorpMapper corpMapper = SpringContextHolderUtil.getBean(CorpMapper.class);
	private static PrivilegeMapper privilegeMapper = SpringContextHolderUtil.getBean(PrivilegeMapper.class);

	public static final String CACHE_AREA_ALL = "areaAll";
	public static final String CACHE_ORG_ALL = "orgAll";
	public static final String CACHE_ROLE_ALL = "roleAll";
	public static final String CACHE_PRIVILEGE_ALL = "privilegeAll"; 
	
	/**
	 * 获取当前所有区域
	 * @return
	 */
	public static List<Area> getAreaAllList(){
		@SuppressWarnings("unchecked")
		List<Area> areaList = (List<Area>)CacheUtil.get(CACHE_AREA_ALL);
		if (null == areaList){
			areaList = areaMapper.findAll(new Area());
			CacheUtil.put(CACHE_AREA_ALL, areaList);
		}
		return areaList;
	}
	
	/**
	 * 获取当前所有部门
	 * @return
	 */
	public static List<Org> getOrgAllList(){
		@SuppressWarnings("unchecked")
		List<Org> orgList = (List<Org>)CacheUtil.get(CACHE_ORG_ALL);
		if (null == orgList){
			orgList = orgMapper.findAll(new Org());
			CacheUtil.put(CACHE_ORG_ALL,orgList);
		}
		return orgList;
	}
	
	/**
	 * 获取当前所有角色
	 * @return
	 */
	public static List<Role> getRoleAllList(){
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>)CacheUtil.get(CACHE_ROLE_ALL);
		if (null == roleList){
			roleList = roleMapper.findAll(new Role());
			CacheUtil.put(CACHE_ROLE_ALL,roleList);
		}
		return roleList;
	}
	
	/**
	 * 获取当前所有权限
	 * @return
	 */
	public static List<Privilege> getPrivilegeAllList(){
		@SuppressWarnings("unchecked")
		List<Privilege> privilegeList = (List<Privilege>)CacheUtil.get(CACHE_PRIVILEGE_ALL);
		if (null == privilegeList){
			privilegeList = privilegeMapper.findAll(new Privilege(null,null));
			CacheUtil.put(CACHE_PRIVILEGE_ALL, privilegeList);
		}
		return privilegeList;
	}
	
	public static List<Privilege> getModules(){
		Privilege privilege = new Privilege();
		privilege.setPid(Global.MENU_ROOT_PID);
		privilege.setType(Global.PRI_MENU);
		return privilegeMapper.findTopMenu(privilege);
	}
	
	public static List<Corp> getAllCorp(){
		return corpMapper.findAll(new Corp());
	}
	
	/**
	 * 清除当前用户缓存
	 */
	public static void clearAllSysCache(){
		CacheUtil.remove(CACHE_AREA_ALL);
		CacheUtil.remove(CACHE_ORG_ALL);
		CacheUtil.remove(CACHE_ROLE_ALL);
		CacheUtil.remove(CACHE_PRIVILEGE_ALL);
	}
}
