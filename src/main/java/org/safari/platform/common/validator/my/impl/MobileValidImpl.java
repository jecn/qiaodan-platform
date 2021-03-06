package org.safari.platform.common.validator.my.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.safari.platform.common.utils.RegexUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.validator.my.MobileValid;

/**
 * <p>Title: 自定义校验规则</p>
 * <p>Description:手机号校验实现</p>
 * <p>Company: 深圳市萨法瑞科技有限公司</p>
 * @author Alitalk 
 * @date 2016-4-12
 */
public class MobileValidImpl implements ConstraintValidator<MobileValid, String>{

	@Override
	public void initialize(MobileValid valid) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(!StringUtil.isEmpty(value)){
			return RegexUtil.isCellphone(value);
		}
		return true;
	}

}
