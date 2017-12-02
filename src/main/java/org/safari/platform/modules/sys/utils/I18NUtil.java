package org.safari.platform.modules.sys.utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.safari.platform.common.utils.BrowserUtil;
import org.safari.platform.common.utils.ServletUtil;
import org.safari.platform.common.utils.SpringContextHolderUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.modules.sys.entity.I18N;
import org.safari.platform.modules.sys.mapper.I18NMapper;

/**
 * 字符串处理及转换工具类
 * @author  Alitalk
 */
public class I18NUtil {
	
	public static Map<String, String> langMap = new HashMap<String, String>(); 
	
    private static I18NMapper i18NMapper;
	
	/**
	 * 获取用户语言编码
	 * @return String
	 */
	public static String getLang(){
		
		HttpServletRequest request = ServletUtil.getRequest();
		//默认使用浏览器的设置的默认语言编码
		String langCode = BrowserUtil.getBrowserLanguage(request);
		
		//若选择的其他语言编码则使用设置后的语言编码
		if(request.getSession().getAttribute("lang") != null){
			langCode = (String)request.getSession().getAttribute("lang");
		}
		
		return langCode;
	}
	
	public static void initI18N() {
		clearCache();
		List<I18N> i18ns = getInstance().findAll(new I18N());
		for (I18N i18n : i18ns) {
			langMap.put(i18n.getLangCode() + "_" + i18n.getLangKey(), i18n.getLangValue());
		}
	}
	
	/**
	 * 根据Key值获取Value
	 * @param key
	 * @return String
	 */
	public static String getLangValue(String langKey) {
		return getLangValue(getLang(), langKey);
	}
	
	/**
	 * 根据语言编码获取语言值
	 * @param langCode
	 * @param langKey
	 * @return String
	 */
	public static String getLangValue(String langCode,String langKey) {
		String langValue = langMap.get(langCode + "_" + langKey); 
		if(StringUtil.isEmpty(langValue)){
			I18N i18n = getInstance().findByCodeAndKey(new I18N(langCode,langKey));
			if(null != i18n){
				langValue = i18n.getLangValue();
				//把查询后结果放在Map中，防止下次使用重新插叙数据库
				langMap.put(langCode + "_" + langKey, langValue);
			}else{
				langValue = langKey;
			}
		}
		return langValue;
	}
	
	/**
	 * 添加至缓存
	 * @param i18N
	 */
	public static void put(I18N i18N){
		put(i18N.getLangCode(),i18N.getLangKey(),i18N.getLangValue());
	}
	
	/**
	 * 修改缓存中的数据
	 * @param i18n
	 */
	public void modify(I18N i18n) {
		modify(i18n.getLangCode(), i18n.getLangKey(), i18n.getLangValue());
	}
	
	/**
	 * 修改缓存中的数据
	 * @param langCode
	 * @param langKey
	 * @param langValue
	 */
	public void modify(String langCode, String langKey, String langValue) {
		//移除原有缓存
		remove(langCode, langKey);
		//添加现有缓存
		put(langCode, langKey, langValue);
	}
	
	/**
	 * 添加至缓存
	 * @param langCode
	 * @param langKey
	 * @param langValue
	 */
	private static void put(String langCode, String langKey, String langValue) {
		langMap.put(langCode + "_" + langKey, langValue);
	}

	/**
	 * 从缓存中移除
	 * @param i18n
	 */
	public void remove(I18N i18n) {
		remove(i18n.getLangCode(), i18n.getLangKey());
	}

	/**
	 * 从缓存中移除
	 * @param langCode
	 * @param langKey
	 */
	public void remove(String langCode, String langKey) {
		langMap.remove(langCode + "_" + langKey);
	}
	
	/**
	 * 清空缓存
	 */
	public static void clearCache(){
		langMap.clear();
	}
	
	public static I18NMapper getInstance(){
		if(i18NMapper == null){
			i18NMapper = SpringContextHolderUtil.getBean(I18NMapper.class);	
		}
		return i18NMapper;
	}
	
}
