package org.safari.platform.common.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.safari.platform.common.config.Global;
import org.safari.platform.common.utils.StringUtil;
import org.slf4j.MDC;

public class UserFilter implements Filter {

	public void init(FilterConfig arg0) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		boolean isRegister = false;
		HttpServletRequest req = (HttpServletRequest) request;
		Principal principal = req.getUserPrincipal();
		
		if(null != principal){
			String username = principal.getName();
			isRegister = registerUsername(username);
		}
		
		try {
			chain.doFilter(request, response);
		} finally{
			if(isRegister){
				MDC.remove(Global.SESSION_USER);
			}
		}
	}

	private boolean registerUsername(String username) {
		if(StringUtil.isNotEmpty(username)){
			MDC.put(Global.SESSION_USER,username);
			return true;
		}
		return false;
	}

	public void destroy() {}

}
