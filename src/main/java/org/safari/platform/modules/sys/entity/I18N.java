package org.safari.platform.modules.sys.entity;

import org.safari.platform.common.persistence.DataEntity;

/**
 * <p>Title: 国际化实体类</p>
 * <p>Description: </p>
 * <p>Company: 深圳市萨法瑞科技有限公司</p>
 * @author Alitalk 
 * @date 2016年5月20日
*/
public class I18N extends DataEntity<I18N> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 语言
	 */
	private String langCode;
	
	/**
	 * 语言主键
	 */
	private String langKey;
	
	/**
	 * 内容
	 */
	private String langValue;
	
	public I18N() {
		super();
	}
	
	public I18N(String id){
		super(id);
	} 
	
	public I18N(String langCode, String langKey) {
		super();
		this.langCode = langCode;
		this.langKey = langKey;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getLangKey() {
		return langKey;
	}

	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}

	public String getLangValue() {
		return langValue;
	}

	public void setLangValue(String langValue) {
		this.langValue = langValue;
	}

}
