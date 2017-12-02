package org.safari.platform.common.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.safari.platform.common.config.Global;

/**
 * Session过滤器
 * @author Alitalk
 * @date 2017-02-10
 */
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

        /*
         * 请求 http://127.0.0.1:8080/webApp/home.jsp?&a=1&b=2 时
         * request.getRequestURL()： http://127.0.0.1:8080/webApp/home.jsp
         * request.getContextPath()： /webApp 
         * request.getServletPath()：/home.jsp
         * request.getRequestURI()： /webApp/home.jsp
         * request.getQueryString()：a=1&b=2
         */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String servletPath = request.getServletPath();
        
        HttpSession session = request.getSession();
        
        System.out.println(session.getAttribute(Global.SESSION_USER));
       
		if(null != session && null != (String)(session.getAttribute(Global.SESSION_USER))
				&& !"".equals((String)(session.getAttribute(Global.SESSION_USER)))
				&& !servletPath.startsWith("/static") &&
				!servletPath.endsWith("/a") && !servletPath.endsWith("/a/login") ){
			request.getRequestDispatcher("/a").forward(request, response);
		}else{
			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
