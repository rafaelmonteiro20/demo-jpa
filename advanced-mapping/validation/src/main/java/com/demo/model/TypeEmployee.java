package com.demo.model;

import com.demo.model.validator.group.FullTime;
import com.demo.model.validator.group.PartTime;

public enum TypeEmployee {

	FULLTIME(FullTime.class),
	PARTTIME(PartTime.class);
	
	private Class<?> group;
	
	private TypeEmployee(Class<?> group) {
		this.group = group;
	}
	
	public Class<?> getGroup() {
		return group;
	}
	
}
