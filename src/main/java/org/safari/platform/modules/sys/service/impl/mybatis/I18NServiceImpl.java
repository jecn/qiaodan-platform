package org.safari.platform.modules.sys.service.impl.mybatis;

import javax.servlet.http.HttpServletRequest;

import org.safari.platform.common.service.BaseMybatisService;
import org.safari.platform.modules.sys.entity.I18N;
import org.safari.platform.modules.sys.mapper.I18NMapper;
import org.safari.platform.modules.sys.service.I18NService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 国际化service实现类(MyBatis)  
 * @author Alitalk
 * @date 2017-02-15
 */
@Service("mI18NService")
@Transactional(readOnly=true)
public class I18NServiceImpl extends BaseMybatisService<I18NMapper, I18N> implements I18NService {

	@Autowired  
	private  HttpServletRequest request; 
	
	@Autowired
	private I18NMapper i18NMapper;
	
	
	@Override
	public void initI18N() {
		
	}

	@Override
	public String getLangValue(String langKey) {
		return null;
	}

	@Override
	public String getLangValue(String langKey, String langArg) {
		return null;
	}

	@Override
	public void reflushCache() {
		
	}

	@Override
	public void put(I18N i18n) {
		
	}

	@Override
	public void motify(String langCode, String langKey, String langValue) {
		
	}

	@Override
	public void motify(I18N i18n) {
		
	}

	@Override
	public void put(String langCode, String langKey, String langValue) {
		
	}

	@Override
	public void remove(I18N i18n) {
		
	}

	@Override
	public void remove(String langCode, String langKey) {
		
	}

	@Override
	public I18N findByCodeAndKey(String langCode, String langKey) {
		I18N i18n = new I18N(langCode, langKey);
		return i18NMapper.findByCodeAndKey(i18n);
	}


}
