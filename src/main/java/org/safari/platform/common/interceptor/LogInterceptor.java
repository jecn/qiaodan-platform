package org.safari.platform.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.safari.platform.common.config.Global;
import org.safari.platform.common.mapper.JsonMapper;
import org.safari.platform.common.utils.IPUtil;
import org.safari.platform.common.utils.SystemUtil;
import org.safari.platform.modules.sys.entity.Log;
import org.safari.platform.modules.sys.utils.LogUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * 日志拦截器
 * @author Alitalk
 * @date 2017-02-10
 */
public class LogInterceptor implements HandlerInterceptor {

	/**
	 * 开始时间
	 */
	private Long beginTime;
	
	/**
	 * 结束时间
	 */
	private Long endTime;

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		beginTime = System.currentTimeMillis();//计时开始
		return true;
	}

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		endTime = System.currentTimeMillis(); //记时结束
		Long time = endTime - beginTime;  //前后耗时
		
		
		String requestRri = request.getRequestURI();
		String method = request.getMethod();  //请求方式
		String param = (new JsonMapper()).toJson(request.getParameterMap());	//请求参数
		
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent")); 
		String os = userAgent.getOperatingSystem().getName();	//获取客户端操作系统
		String browser = userAgent.getBrowser().getName();	//获取客户端浏览器
		
		Log log = new Log();
		log.setBeginTime(String.valueOf(beginTime / 1000));
		log.setTime(Integer.valueOf(time.toString()));
		log.setEndTime(String.valueOf(endTime / 1000));
		log.setOs(os);
		log.setMac(SystemUtil.getMac(os));
		log.setBrowser(browser);
		log.setType(null == ex ? Global.TYPE_ACCESS : Global.TYPE_EXCEPTION);
		log.setIp(IPUtil.getIpAddress(request));
		log.setUserAgent(request.getHeader("User-Agent"));
		log.setLink(requestRri);
		log.setParam(param);
		log.setMethod(method);
		
		//放到一公共变量里，定时提交
		LogUtil.saveLog(request, log, ex);
	}

}
