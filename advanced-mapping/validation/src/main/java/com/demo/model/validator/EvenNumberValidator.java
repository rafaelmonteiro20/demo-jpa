package com.demo.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EvenNumberValidator implements ConstraintValidator<Even, Integer> {

	private boolean includeZero;
	
	@Override
	public void initialize(Even constraintAnnotation) {
		this.includeZero = constraintAnnotation.includeZero();
	}
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if(value == null) {
			return true;
		}
		
		if(value == 0) {
			return includeZero;
		}
		
		return value % 2 == 0;
	}

}
