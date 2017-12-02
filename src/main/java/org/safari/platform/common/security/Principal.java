package org.safari.platform.common.security;

import java.io.Serializable;

import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.utils.UserUtil;

/**
 * 授权用户信息
 */
public class Principal implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键ID
	 */
	private String id; 
	
	/**
	 * 登录名
	 */
	private String username;
	
	/**
	 * 姓名
	 */
	private String name;  
	
	/**
	 * 是否手机登录
	 */
	private boolean mobileLogin; 
	
	public Principal(User user, boolean mobileLogin) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.name = user.getName();
		this.mobileLogin = mobileLogin;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public boolean isMobileLogin() {
		return mobileLogin;
	}

	/**
	 * 获取SESSIONID
	 */
	public String getSessionid() {
		try{
			return (String) UserUtil.getSession().getId();
		}catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public String toString() {
		return id;
	}

}
