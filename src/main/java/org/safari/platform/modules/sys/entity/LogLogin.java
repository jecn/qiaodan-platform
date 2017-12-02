package org.safari.platform.modules.sys.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.persistence.DataEntity;

public class LogLogin extends DataEntity<LogLogin> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 登录时间
	 */
	private Date loginTime;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * IP地址
	 */
	private String ip;
	
	/**
	 * 用户代理
	 */
	private String userAgent;
	
	/**
	 * 操作系统
	 */
	private String os;
	
	/**
	 * 操作浏览器
	 */
	private String browser;

	/**
	 * 物理地址
	 */
	private String mac;
	
	/**
	 * 状态
	 */
	private String stat; 
	
	/**
	 * 信息
	 */
	private String msg;
	
	public LogLogin(){
		super();
	}
	
	public LogLogin(String id){
		super(id);
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Length(max=20)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Length(max=32)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Length(max=255)
	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Length(max=20)
	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	@Length(max=20)
	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Length(max=20)
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	@Length(min=1,max=1)
	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	@Length(max=128)
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	} 
	
}
