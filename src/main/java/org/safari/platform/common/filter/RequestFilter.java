package org.safari.platform.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Request过滤器
 * @author Alitalk
 * @date 2017-02-10
 */
public class RequestFilter implements Filter {  
	
    public static ThreadLocal<HttpServletRequest> threadLocalRequest = new ThreadLocal<HttpServletRequest>();  
  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain filterChain) throws IOException, ServletException {  
    	
        threadLocalRequest.set((HttpServletRequest) request);  
        
        filterChain.doFilter(request, response);  
    }  
  
    public void destroy() {  
    }  
  
    public void init(FilterConfig filterConfig) throws ServletException {  
    }  
}