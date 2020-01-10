package com.xiangwan.flashsale.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.xiangwan.flashsale.util.Util;
/**
 * ConstraintValidator<IsMobile, String> 需要的注解 和 注解修饰字段的类型
 * @author Administrator
 *
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {
	
	private boolean rqeuired = false;
	
	@Override
	public void initialize(IsMobile constraintAnnotation) {
		this.rqeuired = constraintAnnotation.required();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (this.rqeuired) {
			return Util.isMobile(value);
		} else {
			if (StringUtils.isEmpty(value)) {
				return true;
			} else {
				return Util.isMobile(value);
			}
		}
	}
	
}
