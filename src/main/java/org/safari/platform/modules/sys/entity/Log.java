package org.safari.platform.modules.sys.entity;

import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.safari.platform.common.persistence.DataEntity;
import org.safari.platform.common.utils.StringUtil;

/**
 * 日志Entity
 * @author Alitalk
 * @date 2017-02-10
 */
public class Log extends DataEntity<Log> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录者
	 */
	private String creater;
	
	/**
	 * 开始时间
	 */
	private String beginTime;
	
	/**
	 * 日志类型（1：接入日志；2：错误日志）
	 */
	private String type; 
	
	/**
	 * 访问IP
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
	 * 标题
	 */
	private String title;
	
	/**
	 * 操作的URI
	 */
	private String link; 
	
	/**
	 * 操作的方式
	 */
	private String method; 
	
	/**
	 * 操作提交的数据
	 */
	private String param;

	/**
	 * 异常信息
	 */
	private String excp; 
	
	/**
	 * 耗时
	 */
	private int time;	
	
	/**
	 * 结束时间
	 */
	private String endTime;

	public Log(){
		super();
	}
	
	public Log(String id){
		super(id);
	}
	
	@Length(min=6, max=20)
	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	@Length(max=20)
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	@Length(min=1, max=1)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	@Length(max=128)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Length(max=80)
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Length(max=10)
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParam() {
		return param;
	}
	
	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * 设置请求参数
	 * @param paramMap
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setParams(Map paramMap){
		if (paramMap == null){
			return;
		}
		StringBuilder params = new StringBuilder();
		for (Map.Entry<String, String[]> param : ((Map<String, String[]>)paramMap).entrySet()){
			params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
			String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
			params.append(StringUtil.abbr(StringUtil.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
		}
		this.param = params.toString();
	}

	public String getExcp() {
		return excp;
	}

	public void setExcp(String excp) {
		this.excp = excp;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	@Length(max=20)
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}