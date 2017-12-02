package org.safari.platform.common.validator.my.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.safari.platform.common.utils.RegexUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.validator.my.TelValid;

/**
 * <p>Title: 自定义校验规则</p>
 * <p>Description:座机号校验实现</p>
 * <p>Company: 深圳市萨法瑞科技有限公司</p>
 * @author Alitalk 
 * @date 2016-4-12
 */
public class TelValidImpl implements ConstraintValidator<TelValid, String>{

	@Override
	public void initialize(TelValid valid) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(!StringUtil.isEmpty(value)){
			return RegexUtil.isTellphone(value);
		}
		return true;
	}

}
