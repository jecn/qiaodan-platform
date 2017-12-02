package org.safari.platform.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.safari.platform.common.enums.BrowserEnum;

/**
 * <p>Title: 浏览器工具类</p>
 * <p>Description: </p>
 * <p>Company: 深圳市萨法瑞科技有限公司</p>
 * @author Alitalk 
 * @date 2016年5月19日
*/
public class BrowserUtil {
	
	/**
	 * 判断是否是IE
	 * @param request
	 * @return boolean
	 */
	public static boolean isIE(HttpServletRequest request) {
		return (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 || request
				.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0) ? true
				: false;
	}

	/**
	 * 获取IE版本
	 * @param request
	 * @return
	 */
	public static Double getIEversion(HttpServletRequest request) {
		Double version = 0.0;
		if (getBrowserType(request, IE11)) {
			version = 11.0;
		} else if (getBrowserType(request, IE10)) {
			version = 10.0;
		} else if (getBrowserType(request, IE9)) {
			version = 9.0;
		} else if (getBrowserType(request, IE8)) {
			version = 8.0;
		} else if (getBrowserType(request, IE7)) {
			version = 7.0;
		} else if (getBrowserType(request, IE6)) {
			version = 6.0;
		}
		return version;
	}

	/**
	 * 获取浏览器类型
	 * @param request
	 * @return Browsers 浏览器类型
	 */
	public static BrowserEnum getBrowserType(HttpServletRequest request) {
		BrowserEnum browserEnum = null;
		if (getBrowserType(request, IE11)) {
			browserEnum = BrowserEnum.IE11;
		}
		if (getBrowserType(request, IE10)) {
			browserEnum = BrowserEnum.IE10;
		}
		if (getBrowserType(request, IE9)) {
			browserEnum = BrowserEnum.IE9;
		}
		if (getBrowserType(request, IE8)) {
			browserEnum = BrowserEnum.IE8;
		}
		if (getBrowserType(request, IE7)) {
			browserEnum = BrowserEnum.IE7;
		}
		if (getBrowserType(request, IE6)) {
			browserEnum = BrowserEnum.IE6;
		}
		if (getBrowserType(request, FIREFOX)) {
			browserEnum = BrowserEnum.Firefox;
		}
		if (getBrowserType(request, SAFARI)) {
			browserEnum = BrowserEnum.Safari;
		}
		if (getBrowserType(request, CHROME)) {
			browserEnum = BrowserEnum.Chrome;
		}
		if (getBrowserType(request, OPERA)) {
			browserEnum = BrowserEnum.Opera;
		}
		if (getBrowserType(request, "Camino")) {
			browserEnum = BrowserEnum.Camino;
		}
		return browserEnum;
	}

	private static boolean getBrowserType(HttpServletRequest request,
			String brosertype) {
		return request.getHeader("USER-AGENT").toLowerCase()
				.indexOf(brosertype) > 0 ? true : false;
	}

	private final static String IE11 = "rv:11.0";
	private final static String IE10 = "MSIE 10.0";
	private final static String IE9 = "MSIE 9.0";
	private final static String IE8 = "MSIE 8.0";
	private final static String IE7 = "MSIE 7.0";
	private final static String IE6 = "MSIE 6.0";
	private final static String MAXTHON = "Maxthon";
	private final static String QQ = "QQBrowser";
	private final static String GREEN = "GreenBrowser";
	private final static String SE360 = "360SE";
	private final static String FIREFOX = "Firefox";
	private final static String OPERA = "Opera";
	private final static String CHROME = "Chrome";
	private final static String SAFARI = "Safari";
	private final static String OTHER = "其它";

	public static String checkBrowse(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT");
		if (regex(OPERA, userAgent))
			return OPERA;
		if (regex(CHROME, userAgent))
			return CHROME;
		if (regex(FIREFOX, userAgent))
			return FIREFOX;
		if (regex(SAFARI, userAgent))
			return SAFARI;
		if (regex(SE360, userAgent))
			return SE360;
		if (regex(GREEN, userAgent))
			return GREEN;
		if (regex(QQ, userAgent))
			return QQ;
		if (regex(MAXTHON, userAgent))
			return MAXTHON;
		if (regex(IE11, userAgent))
			return IE11;
		if (regex(IE10, userAgent))
			return IE10;
		if (regex(IE9, userAgent))
			return IE9;
		if (regex(IE8, userAgent))
			return IE8;
		if (regex(IE7, userAgent))
			return IE7;
		if (regex(IE6, userAgent))
			return IE6;
		return OTHER;
	}

	public static boolean regex(String regex, String str) {
		Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher m = p.matcher(str);
		return m.find();
	}

	
	private static Map<String, String> langMap = new HashMap<String, String>();
	private final static String ZH = "zh";
	private final static String ZH_CN = "zh_cn";
	
	private final static String EN = "en";
	private final static String EN_US = "en";
	
	
	static 
	{
		langMap.put(ZH, ZH_CN);
		langMap.put(EN, EN_US);
	}
	
	/**
	 * 国际化操作  获取浏览器的使用的语言  
	 * @param request HttpServletRequest
	 * @return String
	 */
	public static String getBrowserLanguage(HttpServletRequest request) {
		String browserLangCode = null;
		if(null != request){
			String browserLang = request.getLocale().getLanguage(); //获取当前浏览器使用的语言
			browserLangCode = (String)langMap.get(browserLang);  //获取语言编码
		}
		
		//默认为中文
		if(StringUtil.isEmpty(browserLangCode)){
			browserLangCode = ZH_CN;
		}
		return browserLangCode;
	}

}
