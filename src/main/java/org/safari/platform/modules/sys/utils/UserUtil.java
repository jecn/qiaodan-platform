package org.safari.platform.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.enums.BarEnum;
import org.safari.platform.common.security.Principal;
import org.safari.platform.common.utils.CacheUtil;
import org.safari.platform.common.utils.SpringContextHolderUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.utils.ThreadPoolUtil;
import org.safari.platform.modules.sys.entity.Area;
import org.safari.platform.modules.sys.entity.Privilege;
import org.safari.platform.modules.sys.entity.Org;
import org.safari.platform.modules.sys.entity.Role;
import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.mapper.AreaMapper;
import org.safari.platform.modules.sys.mapper.PrivilegeMapper;
import org.safari.platform.modules.sys.mapper.OrgMapper;
import org.safari.platform.modules.sys.mapper.RoleMapper;
import org.safari.platform.modules.sys.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户工具类
 * @author Alitalk
 * @date 2017-02-15
 */
public class UserUtil {
	
	protected static Logger LOG = LoggerFactory.getLogger(UserUtil.class);

	private static UserMapper userMapper = SpringContextHolderUtil.getBean(UserMapper.class);
	private static AreaMapper areaMapper = SpringContextHolderUtil.getBean(AreaMapper.class);
	private static RoleMapper roleMapper = SpringContextHolderUtil.getBean(RoleMapper.class);
	private static OrgMapper orgMapper = SpringContextHolderUtil.getBean(OrgMapper.class);
	private static PrivilegeMapper privilegeMapper = SpringContextHolderUtil.getBean(PrivilegeMapper.class);

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "user_id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "login_";
	public static final String USER_CACHE_LIST_BY_AREA_ID_ = "areaListId_";
	public static final String USER_CACHE_LIST_BY_ORG_ID_ = "orgListId_";
	public static final String USER_CACHE_LIST_BY_ROLE_ID_ = "roleListId_"; 
	public static final String USER_CACHE_LIST_BY_PRIVILEGE_ID_ = "privilegeListId_"; 
	public static final String USER_CACHE_TREE_BY_PRIVILEGE_ID_ = "privilegeTreeId_"; 
	public static final String USER_CACHE_MENU_BY_USER_ID_ = "menuId_";
	public static final String USER_CACHE_BAR_BY_USER_ID_ = "barId_";
	public static final String USER_CACHE_LIST_BY_USERBAR_ID_= "userBarId_";
	public static final String USER_CACHE_ATTR_BY_USER_ID_ = "attrId_";
	public static final String USER_CACHE_LIST_BY_USERATTR_ID_= "userAttrId_";
	
	public static final String CACHE_AREA = "userArea";
	public static final String CACHE_ROLE = "userRole";
	public static final String CACHE_ORG = "userOrg";
	public static final String CACHE_PRIVILEGE = "userPrivilege";
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id){
		User user = (User)CacheUtil.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (null == user){
			user = userMapper.get(id);
			if (null == user){
				return null;
			}
			
			user.setRoleList(roleMapper.findUserRoles(new Role(user)));
			user.setOrgList(orgMapper.findList(new Org(user)));
			
			CacheUtil.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtil.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getUsername(), user);
		}
		return user;
	}
	
	/**
	 * 根据登录名获取用户
	 * @param username
	 * @return 取不到返回null
	 */
	public static User getByUsername(String username){
		User user = (User)CacheUtil.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + username);
		if (null == user){
			user = userMapper.findByUsername(new User(username,null));
			if (null == user){
				return null;
			}
			
			user.setRoleList(roleMapper.findUserRoles(new Role(user)));
			user.setOrgList(orgMapper.findList(new Org(user)));
			
			CacheUtil.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtil.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getUsername(), user);
		}
		return user;
	}
	
	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static User getUser(){
		Principal principal = getPrincipal();
		if (null != principal){
			User user = get(principal.getId());
			if (null != user){
				return user;
			}
			return new User();
		}
		return new User();
	}
	
	/**
	 * 获取指定用户角色列表
	 * @return List<Role>
	 */
	@SuppressWarnings("unchecked")
	public static List<Role> getRoleList(User user){
		if(null == user){
			return null;
		}
		
		List<Role> roleList = (List<Role>)getCache(USER_CACHE_LIST_BY_ROLE_ID_ + user.getId());
		if (null == roleList){
			roleList = roleMapper.findUserRoles(new Role(user));
			user.setRoleList(roleList);
			putCache(USER_CACHE_LIST_BY_ROLE_ID_ + user.getId(), roleList);
		}
		return roleList;
	}

	/**
	 * 获取当前用户角色列表
	 * @return List<Role>
	 */
	public static List<Role> getRoleList(){
		return getRoleList(getUser());
	}
	
	/**
	 * 获取指定用户授权的区域
	 * @return List<Area>
	 */
	@SuppressWarnings("unchecked")
	public static List<Area> getAreaList(User user){
		if(null == user){
			return null;
		}
		
		List<Area> areaList = (List<Area>)getCache(USER_CACHE_LIST_BY_AREA_ID_ + user.getId());
		if (null == areaList){
			if (user.isAdmin()){
				areaList = areaMapper.findAll(new Area());
			}else{
				areaList = areaMapper.findList(new Area(user));
			}
			putCache(USER_CACHE_LIST_BY_AREA_ID_ + user.getId(), areaList);
		}
		return areaList;
	}
	
	/**
	 * 获取当前用户授权的区域
	 * @return List<Area>
	 */
	public static List<Area> getAreaList(){
		return getAreaList(getUser());
	}
	
	/**
	 * 获取指定用户有权限访问的部门
	 * @return List<Org>
	 */
	@SuppressWarnings("unchecked")
	public static List<Org> getOrgList(User user){
		if(null == user){
			return null;
		}
		
		List<Org> orgList = (List<Org>)getCache(USER_CACHE_LIST_BY_ORG_ID_ + user.getId());
		if (null == orgList){
			if (user.isAdmin()){
				orgList = orgMapper.findAll(new Org());
			}else{
				Org org = new Org();
//				org.getSqlMap().put("dsf", BaseHibernateService.dataScopeFilter(user, "a", ""));
				orgList = orgMapper.findList(org);
			}
			putCache(USER_CACHE_LIST_BY_ORG_ID_ + user.getId(), orgList);
		}
		return orgList;
	}
	
	/**
	 * 获取当前用户有权限访问的部门
	 * @return List<Org>
	 */
	public static List<Org> getOrgList(){
		return getOrgList(getUser());
	}
	
	/**
	 * 获取当前用户授权
	 * @return List<Privilege>
	 */
	@SuppressWarnings("unchecked")
	public static List<Privilege> getPrivilegeList(User user){
		if(null == user){
			return null;
		}
		
		List<Privilege> privilegeList = (List<Privilege>)getCache(USER_CACHE_LIST_BY_PRIVILEGE_ID_ + user.getId());
		if (null == privilegeList){
			if (user.isAdmin()){
				privilegeList = privilegeMapper.findAll(new Privilege(null,Global.USE_FLAG_YES));
			}else{
				privilegeList = privilegeMapper.findList(new Privilege(null,Global.USE_FLAG_YES,user));
			}
			putCache(USER_CACHE_LIST_BY_PRIVILEGE_ID_ + user.getId(), privilegeList);
		}
		return privilegeList;
	}
	
	/**
	 * 获取当前用户授权
	 * @return List<Privilege>
	 */
	public static List<Privilege> getPrivilegeList(){
		return getPrivilegeList(getUser());
	}
	
	/**
	 * 获取当前用户授权
	 * @return List<Privilege>
	 */
	@SuppressWarnings("unchecked")
	public static List<Privilege> getPrivilegeTree(User user){
		if(null == user){
			return null;
		}
		
		List<Privilege> privilegeList = (List<Privilege>)getCache(USER_CACHE_TREE_BY_PRIVILEGE_ID_ + user.getId());
		if (null == privilegeList){
			if (user.isAdmin()){
				privilegeList = privilegeMapper.findAllTree(new Privilege(null,Global.USE_FLAG_YES));
			}else{
				privilegeList = privilegeMapper.findTree(new Privilege(null,Global.USE_FLAG_YES,user));
			}
			putCache(USER_CACHE_TREE_BY_PRIVILEGE_ID_ + user.getId(), privilegeList);
		}
		return privilegeList;
	}
	
	/**
	 * 获取当前用户授权
	 * @return List<Privilege>
	 */
	public static List<Privilege> getPrivilegeTree() {
		return getPrivilegeTree(getUser());
	}
	
	/**
	 * 获取当前用户授权的菜单
	 * @return String
	 */
	public static String getMenu(){
		User user = getUser();
		if(null == user){
			return null;
		}
		
		JSONArray array = (JSONArray)getCache(USER_CACHE_MENU_BY_USER_ID_ + user.getId());
		if(null == array || array.size() < 1){
			array = new JSONArray();
			boolean adminFlag = false;
			
			Privilege privilege = new Privilege();
			privilege.setPid(Global.MENU_ROOT_PID);
			privilege.setType(Global.PRI_MENU);
			privilege.setUseable(Global.USE_FLAG_YES);
			
			List<Privilege> list = new ArrayList<Privilege>();
			if(user.isAdmin()){
				adminFlag = true;
				list = privilegeMapper.findList(privilege);
			}else{
				privilege.setUser(user);
				list = privilegeMapper.findMenuByUser(privilege);
			}
			
			for (Privilege topPrivilege : list) {
				JSONObject json = new JSONObject();
				
				privilege.setPid(topPrivilege.getId());
				JSONArray child = loadMenu(privilege,adminFlag);
				
				json.put("id", topPrivilege.getId());
				json.put("pid", topPrivilege.getPid());
				json.put("name",topPrivilege.getName());
				json.put("icon", topPrivilege.getIcon());
				if(null != child && child.size() > 0){
					json.put("child", child);
				}else{
					json.put("url", topPrivilege.getUrl());
				}
				array.add(json);
			}
			putCache(USER_CACHE_MENU_BY_USER_ID_ + user.getId(), array);
		}
		return array != null ? array.toString():null;
	}
	
	/**
	 * 获取当前用户拥有的功能按钮权限
	 * @param linsence
	 * @return String
	 */
	public static String getBar(String linsence) {
		return getBar(getUser(),linsence);
	}
	
	/**
	 * 获取用户拥有的功能按钮权限
	 * @param user
	 * @param linsence
	 * @return String
	 */
	public static String getBar(User user,String linsence) {
		if(null == user){
			return null;
		}
		
		String toolbars = (String)getCache(USER_CACHE_LIST_BY_USERBAR_ID_ + user.getId() + linsence);
		if (StringUtil.isEmpty(toolbars)){
			StringBuffer sb = new StringBuffer();
			
			Privilege privilege = new Privilege();
			privilege.setId(linsence);
			privilege.setType(Global.PRI_BTN);
			
			//获取用户拥有的按钮功能权限
			List<Privilege> list = new ArrayList<Privilege>();
			if(user.isAdmin()){
				list = privilegeMapper.findList(privilege);
			}else{
				privilege.setUser(user);
				privilege.setUseable(Global.USE_FLAG_YES);
				list = privilegeMapper.findFunByUser(privilege);
			}
			
			for (Privilege bar : list) {
				String name = bar.getName();
				
				//拼接字符，直接传递到前台显示
				sb.append("<a href='#' class='easyui-linkbutton l-btn l-btn-small l-btn-plain' iconcls='" + BarEnum.toIcon(name) + "' plain='true' onclick='" + BarEnum.toEname(name) + "()'> \n");
				sb.append("  <span class='l-btn-left l-btn-icon-left'> \n");
				sb.append("    <span class='l-btn-text'> \n");
				sb.append("      <span class='platform-title'>" + name + "</span> \n");
				sb.append("    </span> \n");
				sb.append("    <span class='l-btn-icon " + BarEnum.toIcon(name) + "'>&nbsp;</span> \n");
				sb.append("  </span> \n");
				sb.append("</a> \n");
			}
			
			toolbars = sb == null ?null:sb.toString();
			
			//缓存处理
			String userBar = (String)getCache(USER_CACHE_BAR_BY_USER_ID_ + user.getId());
			if(!StringUtil.isEmpty(toolbars)){
				if(!StringUtil.isEmpty(userBar)){
					userBar += "," + linsence;
				}else{
					userBar += linsence;
				}
				putCache(USER_CACHE_BAR_BY_USER_ID_ + user.getId(), userBar);
			}
			
			putCache(USER_CACHE_LIST_BY_USERBAR_ID_ + user.getId() + linsence, toolbars);
		}
		//LOG.info("按钮权限>>>>>>>>>\n" + toolbars);
		return toolbars;
	}
	
	/**
	 * 获取用户拥有的列表格权限
	 * @param linsence
	 * @return String
	 */
	public static String getCols(String linsence) {
		return getCols(getUser(),linsence);
	}
	
	/**
	 * 获取用户拥有的列表格权限
	 * @param user
	 * @param linsence
	 * @return String
	 */
	public static String getCols(User user,String linsence) {
		if(null == user){
			return null;
		}
		
		String columns = (String)getCache(USER_CACHE_LIST_BY_USERATTR_ID_ + user.getId() + linsence);
		if(StringUtil.isEmpty(columns)){
			StringBuffer sb = new StringBuffer();
			
			Privilege privilege = new Privilege();
			privilege.setId(linsence);
			privilege.setType(Global.PRI_ATTR);
			
			//获取用户拥有的表格权限
			List<Privilege> list = new ArrayList<Privilege>();
			if(user.isAdmin()){
				list = privilegeMapper.findList(privilege);
			}else{
				privilege.setUser(user);
				privilege.setUseable(Global.USE_FLAG_YES);
				list = privilegeMapper.findFunByUser(privilege);
			}
			
			if(null != list && list.size() > 0){
				sb.append("[\n");
				sb.append("  {field:'ck',checkbox:true,},\n");
				sb.append("  {field:'id',title:'id',hidden:true},\n");
			
				for (Privilege attr : list) {
					String name = attr.getName();
					String colName = I18NUtil.getLangValue(name);
					
					//获取field值
					int index = name.lastIndexOf(".");
					String field = name.substring(index+1);
					sb.append("  {field:'"+ field +"',title:'" + colName + "',sortable:false},\n");
				}
				sb = new StringBuffer(sb.substring(0, sb.lastIndexOf(",")));
				sb.append("\n]");
			}	
			
			columns = sb == null ? null :sb.toString();
			
			//缓存处理
			String userAttr = (String)getCache(USER_CACHE_ATTR_BY_USER_ID_ + user.getId());
			if(!StringUtil.isEmpty(userAttr)){
				if(!StringUtil.isEmpty(userAttr)){
					userAttr += "," + linsence;
				}else{
					userAttr += linsence;
				}
				putCache(USER_CACHE_ATTR_BY_USER_ID_ + user.getId(), userAttr);
			}
			
			putCache(USER_CACHE_LIST_BY_USERATTR_ID_ + user.getId() + linsence,
					columns);
		}
		//LOG.info("表格权限>>>>>>>>>\n" + columns);
		return columns;
	}

	/**
	 * 循环加载菜单
	 * @param privilege
	 * @param adminFlag 
	 * @param sb 
	 * @return 
	 */
	private static JSONArray loadMenu(Privilege privilege, boolean adminFlag) {
		JSONArray array = new JSONArray();
		List<Privilege> list = new ArrayList<>();
		
		if(adminFlag){//管理员
			list = privilegeMapper.findList(privilege);
		}else{//非管理员
			list = privilegeMapper.findMenuByUser(privilege);
		}
		
		for (Privilege parentPrivilege : list) {
			JSONObject json = new JSONObject();
			
			json.put("id", parentPrivilege.getId());
			json.put("pid", parentPrivilege.getPid());
			json.put("name",parentPrivilege.getName());
			json.put("icon", parentPrivilege.getIcon());
			
			//判断当前菜单记录的url是否为空，若为空则存在子菜单，反之不存在
			if(StringUtil.isEmpty(parentPrivilege.getUrl())){  
				privilege.setPid(parentPrivilege.getId());
				JSONArray child = loadMenu(privilege,adminFlag);
				json.put("child", child);
			}else{
				json.put("url", parentPrivilege.getUrl());
			}
			array.add(json);
		}
		return array;
	}

	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		clearCache(getUser());
	}
	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(User user){
		removeCache(USER_CACHE_LIST_BY_AREA_ID_ + user.getId());
		removeCache(USER_CACHE_LIST_BY_ORG_ID_ + user.getId());
		removeCache(USER_CACHE_LIST_BY_ROLE_ID_ + user.getId());
		removeCache(USER_CACHE_LIST_BY_PRIVILEGE_ID_ + user.getId());
		removeCache(USER_CACHE_TREE_BY_PRIVILEGE_ID_ + user.getId());
		
		clearBar(user);
		clearAttr(user);
		
		CacheUtil.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtil.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getUsername());
	}
	
	/**
	 * 清除用户拥有的功能权限缓存
	 * @param user
	 */
	public static void clearBar(User user){
		String bars = (String)getCache(USER_CACHE_BAR_BY_USER_ID_ + user.getId());
		if(StringUtil.isNotEmpty(bars)){
			String[] barArray = bars.split(",");
			for (String bar : barArray) {
				removeCache(USER_CACHE_LIST_BY_USERBAR_ID_ + user.getId() + bar);
			}
		}
		removeCache(USER_CACHE_BAR_BY_USER_ID_ + user.getId());
	}
	
	/**
	 * 清除用户拥有的列表权限缓存
	 * @param user
	 */
	public static void clearAttr(User user){
		String attrs = (String)getCache(USER_CACHE_ATTR_BY_USER_ID_ + user.getId());
		if(StringUtil.isNotEmpty(attrs)){
			String[] attrArray = attrs.split(",");
			for (String attr : attrArray) {
				removeCache(USER_CACHE_LIST_BY_USERATTR_ID_ + user.getId() + attr);
			}
		}
		removeCache(USER_CACHE_ATTR_BY_USER_ID_ + user.getId());
	}
	
	public static void clearAllUserPrivilegeCache(){
		List<User> list = userMapper.findAll(new User());
		for(User user: list){
			 CacheThread cacheThread = new CacheThread(CACHE_PRIVILEGE,user);
			 ThreadPoolUtil.executor.submit(cacheThread);
		}
	}
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			if (principal != null){
				return principal;
			}
		}catch (UnavailableSecurityManagerException e) {
			
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	/**
	 * 获取当前用户httpsession
	 * @return httpsession
	 */
	public static Session getHttpSession(){
		Session session =SecurityUtils.getSubject().getSession();
		return session;
	}
	
	/**
	 * 获取当前用户对象
	 * @return user
	 */
	public static User getCurrentUser(){
		Session session =SecurityUtils.getSubject().getSession();
		if(null!=session){
			return (User) session.getAttribute("user");
		}else{
			return null;
		}
		
	}
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
		getSession().removeAttribute(key);
	}
	
}
