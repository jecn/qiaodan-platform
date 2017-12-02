package org.safari.platform.modules.sys.utils;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.safari.platform.common.annotation.RequestMethod;
import org.safari.platform.common.config.Global;
import org.safari.platform.common.exception.Exceptions;
import org.safari.platform.common.mapper.JsonMapper;
import org.safari.platform.common.utils.IPUtil;
import org.safari.platform.common.utils.SpringContextHolderUtil;
import org.safari.platform.modules.sys.entity.Log;
import org.safari.platform.modules.sys.entity.LogLogin;
import org.safari.platform.modules.sys.entity.User;
import org.safari.platform.modules.sys.mapper.LogLoginMapper;
import org.safari.platform.modules.sys.mapper.LogMapper;
import org.springframework.web.method.HandlerMethod;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * 字典工具类
 * @author Alitalk
 * @date 2014-11-7
 */
public class LogUtil {
	
	public static final String CACHE_REQUEST_PATH_MAP = "requestPathMap";
	
	private static LogMapper logMapper = SpringContextHolderUtil.getBean(LogMapper.class);
	private static LogLoginMapper logLoginMapper = SpringContextHolderUtil.getBean(LogLoginMapper.class);
	
	/**
	 * 保存日志
	 * @param request
	 * @param log
	 * @param ex
	 */
	public static void saveLog(HttpServletRequest request, Log log, Exception ex){
		saveLog(request, null, log, ex);
	}
	
	/**
	 * 保存登录日志
	 * @param request
	 * @param log
	 */
	public static void saveLog(LogLogin log) {
		new SaveLogLoginThread(log).start();
	}
	/**
	 * 保存日志
	 * @param request
	 * @param handler
	 * @param log
	 * @param ex
	 */
	public static void saveLog(HttpServletRequest request, Object handler, Log log,Exception ex){
		User user = UserUtil.getUser();
		if (null != user){
			log.setCreater(user.getUsername());
			// 异步保存日志
			new SaveLogThread(log, handler, ex, request).start();
		}
	}
	
	/**
	 * 保存登录日志
	 * @param request HttpServletRequest
	 * @param logLogin LogLogin
	 */
	public static void saveLogLogin(HttpServletRequest request, LogLogin logLogin){
		// 异步保存日志
		new SaveLogLoginThread(logLogin).start();
	}

	/**
	 * 保存日志线程
	 */
	public static class SaveLogThread extends Thread{
		
		private Log log;
		private Object handler;
		private Exception ex;
		private HttpServletRequest request;
		
		public SaveLogThread(Log log, Object handler,
				Exception ex,HttpServletRequest request){
			super(SaveLogThread.class.getSimpleName());
			this.log = log;
			this.handler = handler;
			this.ex = ex;
			this.request = request;
		}
		
		@Override
		public void run() {
			
			/*String requestRri = request.getRequestURI(); //获取访问IP  
			String uriPrefix = request.getServletPath();  //获取相对路径
			String link = StringUtils.substringAfter(requestRri,uriPrefix);	//操作编码
			String method = request.getMethod();  //请求方式
			String param = (new JsonMapper()).toJson(request.getParameterMap());	//请求参数
			
			UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent")); 
			String os = userAgent.getOperatingSystem().getName();	//获取客户端操作系统
			String browser = userAgent.getBrowser().getName();	//获取客户端浏览器
			
			log.setOs(os);
			log.setBrowser(browser);
			log.setType(null == ex ? Global.TYPE_ACCESS : Global.TYPE_EXCEPTION);
			log.setIp(IPUtil.getIpAddress(request));
			log.setUserAgent(request.getHeader("User-Agent"));
			log.setLink(link);
			log.setParam(param);
			log.setMethod(method);
			if(Global.TYPE_EXCEPTION.equals(log.getType())){
				log.setExcp(Exceptions.getStackTraceAsString(ex));
			}*/
			
			// 获取日志标题
			if (handler instanceof HandlerMethod){
				Method m = ((HandlerMethod)handler).getMethod();
				RequestMethod rp = m.getAnnotation(RequestMethod.class);
				log.setTitle(rp.title());
			}
			
			// 保存日志信息
			log.preInsert();
			logMapper.insert(log);
		}
	}
	
	/**
	 * 保存日志线程
	 */
	public static class SaveLogLoginThread extends Thread{
		
		private LogLogin logLogin;
		
		public SaveLogLoginThread(LogLogin logLogin){
			super(SaveLogThread.class.getSimpleName());
			this.logLogin = logLogin;
		}
		
		@Override
		public void run() {
			
			// 保存日志信息
			logLogin.preInsert();
			logLoginMapper.insert(logLogin);
		}
	}

	
	
}
