package org.safari.platform.common.validator.my;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.safari.platform.common.validator.my.impl.QQValidImpl;

/**
 * <p>Title: 自定义校验规则</p>
 * <p>Description:QQ号校验接口</p>
 * <p>Company: 深圳市萨法瑞科技有限公司</p>
 * @author Alitalk 
 * @date 2016-4-12
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy = QQValidImpl.class)
@Documented
public @interface QQValid {
	
	String message() default "QQ号输入不正确"; 
	
	Class<?>[] groups() default {}; 
	
	Class<? extends Payload>[] payload() default {};

}
