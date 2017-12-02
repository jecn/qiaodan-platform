package org.safari.platform.modules.sys.mapper;

import java.util.List;

import org.safari.platform.common.annotation.MyBatisMapper;
import org.safari.platform.common.mapper.BaseMapper;
import org.safari.platform.modules.sys.entity.I18N;

@MyBatisMapper
public interface I18NMapper extends BaseMapper<I18N> {
	
	/**
	 * 通过语言编码查找
	 * @param i18n
	 * @return List<I18N>
	 */
	public List<I18N> findByCode(I18N i18n);
	
	/**
	 * 通过语言编码和Key值查找
	 * @param i18n
	 * @return I18N
	 */
	public I18N findByCodeAndKey(I18N i18n);

}
