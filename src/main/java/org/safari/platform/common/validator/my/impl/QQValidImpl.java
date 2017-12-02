package org.safari.platform.common.validator.my.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.safari.platform.common.utils.RegexUtil;
import org.safari.platform.common.utils.StringUtil;
import org.safari.platform.common.validator.my.QQValid;

/**
 * <p>Title: 自定义校验规则</p>
 * <p>Description:QQ号校验实现</p>
 * <p>Company: 深圳市萨法瑞科技有限公司</p>
 * @author Alitalk 
 * @date 2016-4-12
 */
public class QQValidImpl implements ConstraintValidator<QQValid, String>{

	@Override
	public void initialize(QQValid valid) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(!StringUtil.isEmpty(value)){
			return RegexUtil.isQQ(value);
		}
		return true;
	}

}
