package org.safari.platform.common.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.safari.platform.common.utils.PropertiesUtil;
import org.safari.platform.common.utils.StringUtil;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.collect.Maps;

/**
 * 全局配置类
 * @author Alitalk
 * @date 2017-02-10
 */
public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 升序/降序
	 */
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	
	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";
	
	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	
	/**
	 * 是否删除（0:否 1:是）
	 */
	public static final String DEL_NO = "0";
	public static final String DEL_YES = "1";
	
	/**
	 * 是否可用（空：全部；0：不可用；1：可用）
	 */
	public static final String USE_NONE = "";
	public static final String USE_FLAG_NO = "0";
	public static final String USE_FLAG_YES = "1";
	
	/**
	 * 日志类型（1：接入日志；2：错误日志）
	 */
	public static final String TYPE_ACCESS = "1";
	public static final String TYPE_EXCEPTION = "2";
	
	/**
	 * 顶级菜单的父ID为0
	 */
	public static final String MENU_ROOT_PID = "0";
	
	/**
	 * 权限类型( 1: 菜单 2: 功能 3: 按钮 4:属性)
	 */
	public static final String PRI_MENU = "1";
	public static final String PRI_FUN = "2";
	public static final String PRI_BTN = "3";
	public static final String PRI_ATTR = "4";
	
	/**
	 * 登录限制 1 正常 2 禁止登录 3 暂停使用 
	 */
	public static final String LOGIN_NORMAL="1";
	public static final String LOGIN_LIMITED="2";
	public static final String LOGIN_PAUSE="3";
	
	/**Shiro加密方法*/
	public static final String SHIRO_HASH_ALGORITHM = "SHA-1";
	public static final int SHIRO_HASH_INTERATIONS = 1024;
	public static final int SHIRO_SALT_SIZE = 8;	//盐长度
	
	public static final String SESSION_USER = "username";
	
	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";
	
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = PropertiesUtil.getValue(key);
			map.put(key, value != null ? value : StringUtil.EMPTY);
		}
		return value;
	}
	
	/**
	 * 获取管理端根路径
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	/**
	 * 获取前端根路径
	 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	
	/**
	 * 获取URL后缀
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	
	/**
	 * 获取系统名称
	 */
	public static String getProductName() {
		return getConfig("productName");
	}
	
	/**
	 * 获取公司名称
	 */
	public static String getCompanyName() {
		return getConfig("companyName");
	}
	
	/**
	 * 获取单位是否可选
	 */
	public static String getCorpSwitch() {
		return getConfig("corp.switch");
	}
	
	/**
	 * 获取单位主键
	 */
	public static String getCorpId() {
		return getConfig("corp.eventId");
	}
	
	/**
	 * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
	 */
	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}
    
	/**
	 * 页面获取常量
	 * @see ${fns:getConst('YES')}
	 */
	public static Object getConst(String field) {
		try {
			return Global.class.getField(field).get(null);
		} catch (Exception e) {
			// 异常代表无配置，这里什么也不做
		}
		return null;
	}

	/**
	 * 获取上传文件的根目录
	 * @return
	 */
	public static String getUserfilesBaseDir() {
		String dir = getConfig("userfiles.basedir");
		if (StringUtil.isBlank(dir)){
			try {
				WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
				dir =webApplicationContext.getServletContext().getRealPath("/");
			} catch (Exception e) {
				return "";
			}
		}
		if(!dir.endsWith("/")) {
			dir += "/";
		}
		return dir;
	}
	
    /**
     * 获取工程路径
     * @return
     */
    public static String getProjectPath(){
    	// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = Global.getConfig("projectPath");
		if (StringUtil.isNotBlank(projectPath)){
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null){
				while(true){
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()){
						break;
					}
					if (file.getParentFile() != null){
						file = file.getParentFile();
					}else{
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
    }
	
}
