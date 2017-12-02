package org.safari.platform.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.safari.platform.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 禁止黑名单网站访问过滤
 * @author Alitalk
 * @date 2017-02-10
 */
public class BannedAccessFilter implements Filter {
	
	protected static final Logger LOG = LoggerFactory.getLogger(BannedAccessFilter.class);
	
	private HashSet<String> bannedSiteTable;

	@Override
	public void init(FilterConfig config) throws ServletException {
		bannedSiteTable = new HashSet<String>();
		String bannedSites = config.getInitParameter("bannedSites");
		StringTokenizer tok = new StringTokenizer(bannedSites);
		while (tok.hasMoreTokens()) {
			String bannedSite = tok.nextToken();
			bannedSiteTable.add(bannedSite);
			LOG.info("被禁止访问黑名单>>>:  " + bannedSite);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String requestingHost = req.getRemoteHost();
		String referHost = getReferHost(req.getHeader("Referer"));
		
		String bannedSite = null;
		boolean isBanned = false;
		if (bannedSiteTable.contains(requestingHost)) {
			bannedSite = requestingHost;
			isBanned = true;
		} else if (bannedSiteTable.contains(referHost)) {
			bannedSite = referHost;
			isBanned = true;
		}
		
		if (isBanned) {
			showWarning(response, bannedSite);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 根据 URL 链接地址，取得该链接地址所在的站点
	 * @param refererringURLString URL链接地址
	 * @return 该 URL 链接地址所在的站点，如果传入的参数不是一个符合URL规范的字符串，则返回 null
	 */
	private String getReferHost(String refererURL) {
	    if(StringUtil.isBlank(refererURL))
	        return null;
	    
		try {
			URL referURL = new URL(refererURL);
			return referURL.getHost();
		} catch (MalformedURLException mue) { // Malformed
			return null;
		}
	}

	/**
	 * 如果用户是从禁用站点访问的该应用，或是从禁用站点链接过来的，则调用此方法将警告信息展现给用户。
	 * @param response HTTP请求响应对象
	 * @param bannedSite 禁止的站点
	 * @throws ServletException
	 * @throws IOException
	 * @author 何明旺
	 */
	private void showWarning(ServletResponse response, String bannedSite) throws ServletException, IOException {
	    String htmlCode  = "";
	    htmlCode += "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
	    htmlCode += "<html xmlns=\"http://www.w3.org/1999/xhtml\">";
	    htmlCode += "  <head>";
	    htmlCode += "      <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
	    htmlCode += "      <title>禁止访问</title>";
	    htmlCode += "  </head>";
	    htmlCode += "  <body>";
	    htmlCode += "      <h1>禁止访问</h1>";
	    htmlCode += "      <p>对不起，您无法访问该资源，因为您的站点已经被列入我们的黑名单！</p>";
	    htmlCode += "      <p>您的站点是：<strong>" + bannedSite + "</strong></p>";
	    htmlCode += "  </body>";
	    htmlCode += "</html>";

	    response.setContentType("text/html");
	    PrintWriter out = null;
	    try{
	        out = response.getWriter();
    		out.println(htmlCode);
	    }finally{
    	      if(out != null){
    	        out.flush();
    	        out.close();
    	      }
    	}
    	
    	/*
    	 * 也可以使用下面的方法直接转发或重定向到指定的警告页面
    	 * 转发：
    	 *     ((HttpServletRequest)request).getRequestDispatcher("/warn.html").forward(request, response);
    	 * 重定向：
    	 *     ((HttpServletResponse)response).sendRedirect("webAppContext/warn.html");
    	 */
	}

}
