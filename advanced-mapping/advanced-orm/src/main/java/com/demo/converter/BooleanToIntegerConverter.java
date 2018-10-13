package com.demo.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToIntegerConverter implements AttributeConverter<Boolean, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Boolean attribute) {
		if(attribute == null) {
			return null;
		}
		
		return attribute ? 1 : 0;
	}

	@Override
	public Boolean convertToEntityAttribute(Integer dbColumn) {
		if(dbColumn == null) {
			return null;
		}
		
		return dbColumn > 0;
	}

}
